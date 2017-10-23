/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.huiguan.boot.ons.events;

import com.huiguan.boot.ons.EventType;

import java.util.UUID;

/**
 * 余额变更
 *
 * @author huangchunbo
 * @since $$Revision:1.0.0, $$Date: 2017/9/21 20:40 $$
 */
public class BalanceChangeReqEvent implements OnsEvent {

    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 业务类型id
     */
    private Long businessTypeId;

    /**
     * 业务关联
     */
    private String referenceId;

    /**
     * 消息变更的批注
     */
    private String remark;
    /**
     * 是加或者是减
     */
    private Integer opeartion;
    /**
     * 操作的数量
     */
    private Integer num;

    @Override
    public EventType getEventType() {
        return null;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOpeartion() {
        return opeartion;
    }

    public void setOpeartion(Integer opeartion) {
        this.opeartion = opeartion;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMsgKey(){
        return UUID.randomUUID().toString();
    }
}
