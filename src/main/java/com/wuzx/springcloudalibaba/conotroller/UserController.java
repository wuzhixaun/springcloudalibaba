package com.wuzx.springcloudalibaba.conotroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope // 可以帮助我们做局部的参数刷新，但是侵入性较强，需要开发阶提前预知可能的刷新点，并且该注解底层是依赖cglib进行代理的
public class UserController {


    @GetMapping("/getUser")
    public Map<String, Object> getUser() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "000000");
        result.put("message", "ok");
        return result;
    }


    @Value("${xxoo}")
    String configValue;

    @GetMapping("/getValue")
    public String getValue() {
        return configValue;
    }
}
