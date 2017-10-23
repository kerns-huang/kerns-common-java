/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * zk分布式锁测试
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/7/18 下午5:43 $$
 */
@Slf4j
public class LockTests {

    public  void test() throws Exception {
        String connectionString = "121.43.169.171:2181";
        String lockPath = "/test-lock";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(1000, 3));
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        client.start();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i=0;i<5;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.acquire();
                    } catch (Exception e) {
                        log.error("acquire err");
                    }
                    try {
                        Thread.sleep(10000);
                        lock.release();
                    } catch (Exception e) {
                        log.error("biz err");
                        try {
                            lock.release();
                        } catch (Exception e1) {
                            log.error("release err");
                        }
                    }
                }
            });

            Thread.sleep(3000);
        }


    }


}
