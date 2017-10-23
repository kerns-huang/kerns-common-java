/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.global.lock;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 全局锁属性
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/19 上午11:02 $$
 */

@ConfigurationProperties(prefix = "spring.globallock")
public class GlobalLockProperties {

    /**
     * zk/redis 连接串
     */
    private String servers;
    /**
     * 驱动名称(zookeeper/redisson)
     */
    private String drivername;
    /**
     * 锁超时时间毫秒
     */
    private Integer timeout = 1000;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getServers() {
        return this.servers;
    }

    public String getDrivername() {
        return this.drivername;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
