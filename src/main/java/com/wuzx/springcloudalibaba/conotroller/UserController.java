package com.wuzx.springcloudalibaba.conotroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
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
