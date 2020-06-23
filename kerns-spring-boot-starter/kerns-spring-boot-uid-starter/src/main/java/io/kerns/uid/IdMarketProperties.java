package io.kerns.uid;

/**
 * @author xiaohei
 * @create 2020-06-23 下午4:06
 **/

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 发号器属性
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/8/2 上午10:49 $$
 */
@ConfigurationProperties(prefix = "spring.uid")
public class IdMarketProperties {
    private long workerId;

    public long getWorkerId() {
        return this.workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }
}
