package cn.dyaoming.outman.cloud.gateway.common;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.Ordered;

public abstract class BaseGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory implements GatewayFilter, Ordered {

    private int order;

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public GatewayFilter apply(NameValueConfig config) {

        if("order".equals(config.getName())){
            setOrder(Integer.valueOf(config.getValue()));
        }
        System.out.println(this.getClass());
        return this;
    }

}
