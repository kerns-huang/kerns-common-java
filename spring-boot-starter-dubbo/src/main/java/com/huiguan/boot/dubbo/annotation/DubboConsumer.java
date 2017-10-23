/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.annotation;

import java.lang.annotation.*;

/**
 * dubbo 消费者端注解
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DubboConsumer {

    // 版本
    String version() default "";

    // 远程调用超时时间(毫秒)
    int timeout() default 1000;

    // 注册中心
    String registry() default "";

    // 服务分组
    String group() default "";

    // 客户端类型
    String client() default "";

    // 点对点直连服务提供地址
    String url() default "";

    String protocol() default "";

    // 检查服务提供者是否存在
    boolean check() default true;

    // lazy create connection
    boolean lazy() default false;

    // 重试次数
    int retries() default 0;

    // 最大并发调用
    int actives() default 0;

    // 负载均衡
    String loadbalance() default "";

    // 是否异步
    boolean async() default false;

    // 异步发送是否等待发送成功
    boolean sent() default false;
}
