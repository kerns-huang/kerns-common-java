/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.dubbo.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.huiguan.boot.dubbo.domain.ClassIdBean;
import com.huiguan.boot.dubbo.domain.SpringBootStarterDobboConstants;

/**
 * dubbo 提供者统计
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/19 上午10:02 $$
 */
@Activate(group = Constants.PROVIDER)
public class ProviderInvokeStaticsFilter extends StaticsFilterHelper {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Class<?> anInterface = invoker.getInterface();
        URL url = invoker.getUrl();
        String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
        String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
        ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
        increase(classIdBean, invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
