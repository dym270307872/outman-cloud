package cn.dyaoming.outman.cloud.gateway.config;

import cn.dyaoming.outman.cloud.gateway.handle.JsonExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

//@Configuration
public class ExceptionHandleConfiguration {


//    @Primary
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
//                                                             ServerCodecConfigurer serverCodecConfigurer) {
//        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
//        jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
//        jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
//        jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
//        return jsonExceptionHandler;
//    }
}
