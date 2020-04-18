package com.xd.elasticsearch.repository.bean;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户表
 * @author kerns
 * @since 2019-11-26
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7930371866124379726L;
    private Integer id ;
    /**
     * 用户昵称
     */
    private String nickname ;
    /**
     * 手机号
     */
    private Long mobile ;
    /**
     * 国际区号
     */
    private String areaCode ;
    /**
     * 电子邮箱
     */
    private String email ;
    /**
     * 密码 password_hash()
     */
    private String password ;
    /**
     * 等级
     */
    private Integer level ;
    /**
     * 积分
     */
    private Integer score ;
    /**
     * 年龄
     */
    private Integer age ;
    /**
     * 性别 0 未知 1:女 2:男
     */
    private Integer gender ;
    /**
     * 真实姓名
     */
    private String realname ;
    /**
     * 用户头像路径
     */
    private String avatar ;
    /**
     * 外键 国家id 关联 cat_country
     */
    private Integer country ;
    /**
     * 省
     */
    private String province ;
    /**
     * 市
     */
    private String city ;
    /**
     * 县
     */
    private String county ;
    /**
     * 所属工会
     */
    private Integer sociatyId ;
    private Date joinSociaty ;
    private Byte shareRate ;
    /**
     * 是否是公会长：0否，1是
     */
    private Integer isSociaty ;
    /**
     * 是否认证主播：0否，1是
     */
    private Integer isAnchor ;
    /**
     * 是否明星/网红：0否，1是
     */
    private Integer isStar ;
    /**
     * 是否推荐：0否，1是
     */
    private Integer isRecommend ;
    /**
     * 是否选聊推荐：0否，1是
     */
    private Integer isChatRecommend ;
    /**
     * 是否精选：0否，1是
     */
    private Integer isFeatured ;
    /**
     * 是否开启抢单通知：0否，1是
     */
    private Integer isOpenGrabNotice ;
    /**
     * 是否实名认证：0否，1是
     */
    private Integer isCertified ;
    /**
     * 视频开启：0关闭，1打开
     */
    private Integer videoOn ;
    /**
     * 视频每分钟收费
     */
    private BigDecimal videoFee ;
    /**
     * 私信开启：0关闭，1打开
     */
    private Integer pmOn ;
    /**
     * 私信每条收费
     */
    private BigDecimal pmFee ;
    /**
     * 是否开启消息震动提醒 0:关闭,1:开启
     */
    private Integer msgNotice ;
    private String imAccid ;
    /**
     * 上线用户
     */
    private Integer upperUid ;
    /**
     * 邀请码
     */
    private String inviteCode ;
    private Integer channelId ;
    /**
     * 注册时间
     */
    private Date joinDate ;
    /**
     * 注册ip
     */
    private Long joinIp ;
    /**
     * 是否激活
     */
    private Integer active ;
    /**
     * vip 过期日期
     */
    private Date vipExpire ;
    /**
     * svip过期时间
     */
    private Date svipExpire ;
    /**
     * 状态：0正常，1禁用
     */
    private Integer status ;
    /**
     * 是否黑名单：0否，1是
     */
    private Boolean isBlack ;
    /**
     * 余额
     */
    private BigDecimal eurcBalance ;
    private BigDecimal eurcFreeze ;
    private BigDecimal eurcLock ;
    private BigDecimal msqBalance ;
    private BigDecimal msqFreeze ;
    private BigDecimal msqLock ;
}
