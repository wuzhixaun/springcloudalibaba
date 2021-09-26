package com.wuzx.springcloudalibaba.model;

import java.util.Date;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName ServerInfo.java
 * @Description 服务器信息
 * @createTime 2021年09月14日 14:58:00
 */
public class ServerInfo {
    private String userName; // 用户名
    private String computerName; // 计算机名
    private String userDomain; // 计算机域名
    private Date serverTime; // 服务器时间
    private Long totalMemory; // JVM可以使用的总内存
    private Long freeMemory; // JVM可以使用的剩余内存
    private String javaVersion; // java 版本


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public Long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "userName='" + userName + '\'' +
                ", computerName='" + computerName + '\'' +
                ", userDomain='" + userDomain + '\'' +
                ", serverTime=" + serverTime +
                ", totalMemory=" + totalMemory +
                ", freeMemory=" + freeMemory +
                ", javaVersion='" + javaVersion + '\'' +
                '}';
    }
}
