package cn.dyaoming.outman.cloud.servers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DemoServer {

    @Autowired
    private RestTemplate restTemplate;

    public String test(){
        return restTemplate.postForObject("http://CLOUD-SERVICE/demo/test","",String.class);
    }
}
