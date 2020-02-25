package cn.dyaoming.outman.cloud.gateway.filter;

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
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Component
@Slf4j
public class PostParameterGatewayFilterFactory implements GlobalFilter, Ordered {

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
			CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
			DataBufferUtils.release(buffer);
			bodyRef.set(charBuffer.toString());
//            byte[] bytes = new byte[buffer.readableByteCount()];
//            buffer.read(bytes);
//            DataBufferUtils.release(buffer);
//            try {
//                String bodyString = new String(bytes, "utf-8");
//                System.out.println(bodyString);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
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