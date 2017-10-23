/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * topic 前缀缓存
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/22 上午11:43 $$
 */
public class TopicPreHolder {

    private static String topicPre = "test_";

    public static String getTopicPre() {
        return topicPre;
    }

    public static String setTopicPre(String newTopicPre) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(newTopicPre), "topicPre must be not null");
        return topicPre = newTopicPre;
    }
}
