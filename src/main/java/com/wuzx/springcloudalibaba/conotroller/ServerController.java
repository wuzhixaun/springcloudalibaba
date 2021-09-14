package com.wuzx.springcloudalibaba.conotroller;

import com.wuzx.springcloudalibaba.model.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName HealthController.java
 * @Description 服务器
 * @createTime 2021年09月14日 14:50:00
 */

@RestController
public class ServerController {
    private static Logger logger = Logger.getLogger("ServerController");

    @GetMapping("/healthz")
    public ServerInfo getHealthInfo() {
        logger.info("ServerController getHealthInfo");
        ServerInfo serverInfo = new ServerInfo();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        Map<String, String> map = System.getenv();
        serverInfo.setUserName(map.get("USERNAME"));// 获取用户名
        serverInfo.setComputerName(map.get("COMPUTERNAME")); // 计算机名
        serverInfo.setUserDomain(map.get("USERDOMAIN")); // 计算机域名
        serverInfo.setTotalMemory(r.totalMemory()); // JVM可以使用的总内存
        serverInfo.setFreeMemory(r.freeMemory()); // JVM可以使用的剩余内存
        serverInfo.setJavaVersion(props.getProperty("java.version")); // Java的运行环境版本
        serverInfo.setServerTime(new Date());
        int a = 100 / 0;
        return serverInfo;
    }
}
