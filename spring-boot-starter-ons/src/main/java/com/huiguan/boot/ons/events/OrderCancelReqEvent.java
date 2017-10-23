/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.huiguan.boot.ons.events;

import com.huiguan.boot.ons.EventType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单取消事件
 *
 * @author juyongcheng
 * @since $$Revision:1.0.0, $$Date: 2017/9/19 上午9:45 $$
 */
public class OrderCancelReqEvent implements OnsEvent {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 补偿积分
     */
    private Long compensateAccountPoint;
    /**
     * 补偿账户余额
     */
    private Long compensateAccountBalance;
    /**
     * 补偿商家余额
     */
    private Long compensateMerchantBalance;

    /**
     *
     */
    private List<Long> compensateCoupon;
    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 消息类别
     */
    private EventType eventType = EventType.ORDER_CANCEL_REQ;

    public Long getOrderId() {
        return this.orderId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public Long getCompensateAccountPoint() {
        return this.compensateAccountPoint;
    }

    public Long getCompensateAccountBalance() {
        return this.compensateAccountBalance;
    }

    public Long getCompensateMerchantBalance() {
        return this.compensateMerchantBalance;
    }

    public Date getCancelTime() {
        return this.cancelTime;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setCompensateAccountPoint(Long compensateAccountPoint) {
        this.compensateAccountPoint = compensateAccountPoint;
    }

    public void setCompensateAccountBalance(Long compensateAccountBalance) {
        this.compensateAccountBalance = compensateAccountBalance;
    }

    public void setCompensateMerchantBalance(Long compensateMerchantBalance) {
        this.compensateMerchantBalance = compensateMerchantBalance;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getMsgKey(){
        return UUID.randomUUID().toString();
    }

}
