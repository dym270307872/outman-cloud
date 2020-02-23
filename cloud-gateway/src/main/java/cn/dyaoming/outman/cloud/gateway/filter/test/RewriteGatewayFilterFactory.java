package cn.dyaoming.outman.cloud.gateway.filter.test;

import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class RewriteGatewayFilterFactory extends RewritePathGatewayFilterFactory implements Ordered {
    @Override
    public int getOrder() {
        return 3;
    }
}
