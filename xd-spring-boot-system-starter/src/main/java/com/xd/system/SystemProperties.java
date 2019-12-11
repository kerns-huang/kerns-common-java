package com.xd.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * 系统变量设置
 *
 * @author xiaohei
 * @create 2019-12-10 下午5:54
 **/
@Data
@Configuration
@ConfigurationProperties(prefix="system")
public class SystemProperties {
    private SystemProperties.Limit limit;
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
