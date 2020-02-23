package cn.dyaoming.outman.cloud.constrollers;

import cn.dyaoming.outman.cloud.servers.DemoServer;
import cn.dyaoming.outman.cloud.servers.FeignServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoConstroller {

    @Autowired
    private DemoServer demoServer;

    @Autowired
    private FeignServer feignServer;

    @RequestMapping("test")
    public String test(){
        return demoServer.test();
    }

    @RequestMapping("/feignTest")
    public String testFeign(){
        return feignServer.test();
    }
}
