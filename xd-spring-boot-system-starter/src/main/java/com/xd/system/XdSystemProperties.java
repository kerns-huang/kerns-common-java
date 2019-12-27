package com.xd.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.math.BigDecimal;

/**
 * 系统变量设置 cat_limit 表变更
 *
 * @author xiaohei
 * @create 2019-12-10 下午5:54
 **/
@Data
@ConfigurationProperties(prefix="system")
public class XdSystemProperties {
    @NestedConfigurationProperty
    private XdSystemProperties.Limit limit;
    @Data
    public static class Limit {
        private BigDecimal videoFeeMin;

        private BigDecimal videoFeeMax;

        private BigDecimal videoFeeDefault;

        private BigDecimal pmFeeMin;

        private BigDecimal pmFeeMax;

        private BigDecimal pmFeeDefault;
    }
}
