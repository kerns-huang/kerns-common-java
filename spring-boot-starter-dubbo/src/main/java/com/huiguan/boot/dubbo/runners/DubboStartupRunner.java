/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * 后置runner
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
public class DubboStartupRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DubboStartupRunner.class);
    private String appName;

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(appName.concat(" started..."));
        /*
        try {
            new CountDownLatch(1).await();
        } catch (Throwable t) {
            log.error(appName.concat(" err..."), t);
            throw new Exception(t);
        }
        */
    }
}
