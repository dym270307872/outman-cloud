package cn.dyaoming.outman.cloud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(){
        return "随机数：" + new Random().nextInt(100);
    }
}
