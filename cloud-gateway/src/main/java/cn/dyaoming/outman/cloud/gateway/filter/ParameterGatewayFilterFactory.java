package cn.dyaoming.outman.cloud.gateway.filter;

import cn.dyaoming.outman.cloud.gateway.common.BaseGatewayFilterFactory;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class ParameterGatewayFilterFactory implements GlobalFilter, Ordered {

    //提取版本号及接口编码正则
    String regVersionAndInterfaceCode = "(?<=/[v|V])([^/]*)/([^/]*)";
    //提取版本号正则
    String regVersion = "(?<=/)[v|V]([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|\\d+)?(?=/)";
    //提取接口编码正则
    String regInterfaceCode = "(?<=api/)([^/]*)";

    String SessionObjectKey = "terminalId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.error("ParameterGatewayFilterFactory运行");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();

        Map<String,Object> params = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.putAll(serverHttpRequest.getHeaders());
        if(headers.containsKey(SessionObjectKey)){
            //TODO 依据实际情况追加参数，也可以在此拦截器前增加登录认证拦截器，在对应拦截器中处理此参数。
            Object sessionObject = headers.getFirst(SessionObjectKey);
            params.put(SessionObjectKey,sessionObject);
        }


        if ("POST".equals(method)) {
            //从请求里获取Post请求体
            String bodyStr = resolveBodyFromRequest(serverHttpRequest);
            //TODO 得到Post请求的请求参数后，做你想做的事

            //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            URI uri = serverHttpRequest.getURI();
            ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

            request = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            //封装request，传给下一级
            return chain.filter(exchange.mutate().request(request).build());
        } else if ("GET".equals(method)) {
            URI uri = serverHttpRequest.getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();

            if (StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }

            Matcher param = Pattern.compile(regVersionAndInterfaceCode).matcher(uri.getPath());
            if (param.find()) {
                String parameter = param.group();
                query.append("version=" + parameter.split("/")[0]);
                query.append('&');
                query.append("interfaceCode=" + parameter.split("/")[1]);
                query.append('&');
            }else{
                Matcher macInterfaceCode = Pattern.compile(regInterfaceCode).matcher(uri.getPath());
                if(macInterfaceCode.find()) {
                    query.append("interfaceCode=" + macInterfaceCode.group());
                    query.append('&');
                }
            }

            for(String key : params.keySet()){
                query.append(key + "=" + params.get(key));
                query.append('&');
            }

            log.error(query.toString());
            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(query.toString())
                        .build(true)
                        .toUri();
                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
            }
        }
        return chain.filter(exchange);
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
//			CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
//			DataBufferUtils.release(buffer);
//			bodyRef.set(charBuffer.toString());
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            try {
                String bodyString = new String(bytes, "utf-8");
                System.out.println(bodyString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        //获取request body
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Override
    public int getOrder() {
        return 20000;
    }
}
