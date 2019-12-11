package com.xd.system;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置
 *
 * @author xiaohei
 * @create 2019-12-11 上午9:59
 **/
@Configuration
@EnableConfigurationProperties(XdSystemProperties.class)
public class SystemConf {
}
