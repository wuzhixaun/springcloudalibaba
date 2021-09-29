package com.wuzx.springcloudalibaba.conotroller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ApiSupport(author = "wuzhixuan@foxmail.com",order = 2)
@Api(tags = "用户模块")
@RestController
@RefreshScope // 可以帮助我们做局部的参数刷新，但是侵入性较强，需要开发阶提前预知可能的刷新点，并且该注解底层是依赖cglib进行代理的
public class UserController {

    @ApiOperationSupport(author = "小猪",order = 200)
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getUser")
    public Map<String, Object> getUser() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "000000");
        result.put("message", "ok");
        return result;
    }


    @Value("${xxoo}")
    String configValue;
    @ApiOperationSupport(order = 100)
    @ApiOperation(value = "获取配置文件的值")
    @GetMapping("/getValue")
    public String getValue() {
        return configValue;
    }


    @ApiOperationSupport(author = "hell", order = 1)
    @ApiOperation(value = "根据用户名和年龄获取用户信息")
    @GetMapping("/getUserByAge")
    public Map<String, Object> getUserByCode(String name, int age) {

        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        return result;
    }


    @ApiOperationSupport(author = "hell", order = 1)
    @ApiOperation(value = "查询")
    @GetMapping("/find")
    public Map<String, Object> find(String name, int age) {

        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        return result;
    }
}
