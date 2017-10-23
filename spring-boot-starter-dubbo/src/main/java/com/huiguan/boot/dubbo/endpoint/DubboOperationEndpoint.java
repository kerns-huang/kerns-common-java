/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.endpoint;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.huiguan.boot.dubbo.DubboProperties;
import com.huiguan.boot.dubbo.listener.ProviderExportListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * dubbo 操作连接点
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@RestController
public class DubboOperationEndpoint implements MvcEndpoint {
    @Autowired
    private DubboProperties dubboProperties;

    private Registry registry;

    @RequestMapping("/online")
    public List<Map<String, String>> online() {
        List<Map<String, String>> serviceInterfaceList = new LinkedList<Map<String, String>>();
        if (!ProviderExportListener.EXPORTED_URL.isEmpty()) {
            this.init();
            for (URL url : ProviderExportListener.EXPORTED_URL) {
                this.registry.register(url);
                Map<String, String> map = url.toMap();
                serviceInterfaceList.add(map);
            }
        }
        return serviceInterfaceList;
    }

    @RequestMapping("/offline")
    public List<Map<String, String>> offline() {
        List<Map<String, String>> serviceInterfaceList = new LinkedList<Map<String, String>>();
        if (!ProviderExportListener.EXPORTED_URL.isEmpty()) {
            this.init();
            for (URL url : ProviderExportListener.EXPORTED_URL) {
                this.registry.unregister(url);
                Map<String, String> map = url.toMap();
                serviceInterfaceList.add(map);
            }
        }
        return serviceInterfaceList;
    }

    /**
     * 初始化registry
     */
    private synchronized void init() {

        if (this.registry == null) {
            ExtensionLoader<RegistryFactory> extensionLoader =
                    ExtensionLoader.getExtensionLoader(RegistryFactory.class);
            URL url = URL.valueOf(this.dubboProperties.getRegistry());
            RegistryFactory registryFactory = extensionLoader.getDefaultExtension();
            this.registry = registryFactory.getRegistry(url);
        }
    }

    @Override
    public String getPath() {
        return "dubbo";
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Class<? extends Endpoint<?>> getEndpointType() {
        return null;
    }
}
