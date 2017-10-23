/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.starter.dubbozipkin;

import java.util.Map;

import com.alibaba.dubbo.rpc.Result;
import com.huiguan.commons.results.CommonStateCodes;
import com.huiguan.commons.results.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.Tracer;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * 消费者端spanfilter
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/17 下午1:42 $$
 */

@Activate(group = { Constants.CONSUMER }, order = -9000)
@Slf4j
public class ConsumerSpanFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean isTraceDubbo = false;
        Tracer tracer = null;
        SpanInjector spanInjector = null;
        Span span = null;
        Result rpcErrResult=new RpcResult(Results.newFailedResult(CommonStateCodes.GATEWAY_RPC_ERROR));
        try {
            Map<String, String> attachments = RpcContext.getContext().getAttachments();
            tracer = ApplicationContextAwareBean.CONTEXT.getBean(Tracer.class);
            spanInjector = ApplicationContextAwareBean.CONTEXT.getBean(DubboSpanInjector.class);
            isTraceDubbo = (tracer != null && spanInjector != null);
            Throwable exception=null;
            if (isTraceDubbo) {
                String spanName = invoker.getUrl().getParameter("interface") + ":"
                                  + invocation.getMethodName() + ":"
                                  + invoker.getUrl().getParameter("version") + "("
                                  + invoker.getUrl().getHost() + ")";
                Span parent = tracer.getCurrentSpan();
                if (null == parent) {
                    span = tracer.createSpan(spanName);
                } else {
                    span = tracer.createSpan(spanName, parent);
                }
                spanInjector.inject(span, RpcContext.getContext());
                span.logEvent(Span.CLIENT_SEND);
            }
            Result result=null;

            try {
                result = invoker.invoke(invocation);
                /*if((null==result.getValue())||(!(result.getValue() instanceof com.huiguan.commons.results.DubboResult) && !(result.getValue() instanceof com.huiguan.commons.results.Result))){
                    throw new RuntimeException("The Dubbo interface definition must return an instance of com.huiguan.commons.results.DubboResult ");
                }*/
                if (result.hasException()){
                    handleException(invoker,invocation,result.getException(),tracer);
                    return rpcErrResult;
                }
                return result;
            }catch(Throwable e){
                handleException(invoker,invocation,e,tracer);
                return rpcErrResult;
            }
        } finally {
            if (isTraceDubbo && span != null) {
                tracer.getCurrentSpan().logEvent(Span.CLIENT_RECV);
                tracer.close(tracer.getCurrentSpan());
            }
        }
    }

    /**
     * 处理rpc异常
     * @param invoker invoker
     * @param invocation invocation
     * @param exception exception
     */
    private void handleException(Invoker<?> invoker, Invocation invocation, Throwable exception, Tracer tracer){
        log.warn("Fail to execute invoker when called by " + RpcContext.getContext().getRemoteHost()
                + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);
        tracer.addTag("RPC_ERR", org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(exception));
    }

}
