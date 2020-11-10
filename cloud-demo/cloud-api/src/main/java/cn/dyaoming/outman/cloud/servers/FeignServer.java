package cn.dyaoming.outman.cloud.servers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("cloud-service")
public interface FeignServer {

    @RequestMapping(value = "/demo/test", method = RequestMethod.POST)
    public String test();

}
