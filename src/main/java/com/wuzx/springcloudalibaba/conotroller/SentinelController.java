package com.wuzx.springcloudalibaba.conotroller;


import com.wuzx.springcloudalibaba.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelController {


    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/get")
    public String get() {
        return sentinelService.getBody();
    }
}
