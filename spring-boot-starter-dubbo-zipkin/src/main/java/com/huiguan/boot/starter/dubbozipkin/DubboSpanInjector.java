/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.starter.dubbozipkin;

import java.util.Map;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;

import com.alibaba.dubbo.rpc.RpcContext;

import lombok.extern.slf4j.Slf4j;

/**
 * span参数传递
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/5/17 下午1:42 $$
 */
@Slf4j
public class DubboSpanInjector implements SpanInjector<RpcContext> {
    @Override
    public void inject(Span span, RpcContext carrier) {

        Object[] parameter = carrier.getArguments();
        Map<String, String> attachments = carrier.getAttachments();
        if (span.getTraceId() != 0) {
            attachments.put(Span.TRACE_ID_NAME, Span.idToHex(span.getTraceId()));
        }
        if (span.getSpanId() != 0) {
            attachments.put(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
        }
        attachments.put(Span.SAMPLED_NAME,
            span.isExportable() ? Span.SPAN_SAMPLED : Span.SPAN_NOT_SAMPLED);
        attachments.put(Span.SPAN_NAME_NAME, span.getName());
        Long parentId = getParentId(span);
        if (parentId != null && parentId != 0) {
            attachments.put(Span.PARENT_ID_NAME, Span.idToHex(parentId));
        }
        attachments.put(Span.PROCESS_ID_NAME, span.getProcessId());
        if(null==span) {
            return;
        }
        if (null != parameter && parameter.length > 0) {
            span.tag("C_REQUEST", com.alibaba.fastjson.JSON.toJSONString(parameter));
        } else {
            span.tag("C_REQUEST", "null");
        }
    }

    private Long getParentId(Span span) {
        return !span.getParents().isEmpty() ? span.getParents().get(0) : null;
    }

}
