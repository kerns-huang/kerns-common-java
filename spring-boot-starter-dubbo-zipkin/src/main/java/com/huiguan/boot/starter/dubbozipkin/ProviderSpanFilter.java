/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.starter.dubbozipkin;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.*;

import java.util.Map;

/**
 * 提供者端span filter
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/17 下午1:42 $$
 */


@Activate(group = {Constants.PROVIDER}, order = -9000)
@Slf4j
public class ProviderSpanFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean isTraceDubbo = false;
        Tracer tracer = null;
        SpanExtractor spanExtractor = null;
        SpanInjector spanInjector = null;
        SpanReporter spanReporter = null;
        Span span = null;
        Throwable exception = null;
        try {
            Map<String, String> attachments = RpcContext.getContext().getAttachments();
            tracer = ApplicationContextAwareBean.CONTEXT.getBean(Tracer.class);
            spanExtractor = ApplicationContextAwareBean.CONTEXT.getBean(DubboSpanExtractor.class);
            spanInjector = ApplicationContextAwareBean.CONTEXT.getBean(DubboSpanInjector.class);
            spanReporter = ApplicationContextAwareBean.CONTEXT.getBean(SpanReporter.class);

            isTraceDubbo = (tracer != null && spanExtractor != null && spanInjector != null && spanReporter != null);
            if (isTraceDubbo) {
                //String spanName = invoker.getUrl().getParameter("interface") + ":" + invocation.getMethodName() + ":" + invoker.getUrl().getParameter("version") + "(" + invoker.getUrl().getHost() + ")";
                span = spanExtractor
                        .joinTrace(RpcContext.getContext());
                if (span != null) {
                    span=tracer.continueSpan(span);
                    span.logEvent(Span.SERVER_RECV);
                }
            }
            Result result = invoker.invoke(invocation);
            if(null==span) {
                return result;
            }
            if (null != result) {
                span.tag("S_RESPONSE", JSON.toJSONString(result));
            } else {
                span.tag("S_RESPONSE", "null");
            }
            return result;
        }
        finally {
            if (isTraceDubbo && span != null) {
                tracer.getCurrentSpan().logEvent(Span.SERVER_SEND);
                //tracer.close(tracer.getCurrentSpan());
                span.stop();
                spanReporter.report(span);
            }
        }

    }
}
