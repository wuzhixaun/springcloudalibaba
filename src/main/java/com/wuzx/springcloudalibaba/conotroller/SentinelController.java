package com.wuzx.springcloudalibaba.conotroller;


import com.wuzx.springcloudalibaba.service.SentinelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "sentinel网关")
@RestController
@RequestMapping("/sentinel")
public class SentinelController {


    @Autowired
    private SentinelService sentinelService;

    @ApiOperation(value = "获取sentinel值")
    @GetMapping("/get")
    public String get() {
        return sentinelService.getBody();
    }
}
