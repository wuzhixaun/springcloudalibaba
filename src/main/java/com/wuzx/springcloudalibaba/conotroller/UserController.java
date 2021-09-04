package com.wuzx.springcloudalibaba.conotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @GetMapping("/getUser")
    public Map<String, Object> getUser() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "000000");
        result.put("message", "ok");
        return result;
    }
}
