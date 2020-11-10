package cn.dyaoming.outman.cloud.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RefreshScope
@RequestMapping("/demo")
public class DemoController {

    @Value("${config.value}")
    private String configValue;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "随机数：" + new Random().nextInt(100);
    }


    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config() {
        return "configValue：" + configValue;
    }
}
