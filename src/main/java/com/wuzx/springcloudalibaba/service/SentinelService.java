package com.wuzx.springcloudalibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {


    @SentinelResource(value = "get",blockHandler = "getBodyBack")
    public String getBody() {
        // 真正的业务逻辑
        // 被保护的资源
        return "给你我的肉体哦";
    }


    public String getBodyBack(BlockException blockException) {
        return "降级了";
    }
}
