package com.xd.elasticsearch.repository.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表
 * @author zebra
 */
@Setter
@Getter
public class SnsBlog implements Serializable {

    private static final long serialVersionUID = -2526841494625537478L;
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid ;
    /**
     * 昵称
     */
    private String nickname ;
    /**
     * 头像上传路径
     */
    private String avatar ;
    /**
     * 性别：0未知，1女，2男
     */
    private Integer gender ;
    /**
     * 年纪
     */
    private Integer age ;
    /**
     * 国家id 外键
     */
    private Integer country ;
    /**
     * 查询有短视频的动态 || 查询纯短文动态 || 查询图片的动态
     * 查询用户(昵称搜索)(用户状态正常) || 查询直播(实名) (主播认证)(账号正常)(昵称搜索)
     */
    private Integer type ;
    /**
     * 文字内容
     */
    private String content ;
    /**
     * 图片id，多个图片以逗号分隔，如1234,2545,2546
     */
    private String pic ;
    /**
     * 视频id
     */
    private String video ;
    /**
     * 发布时间
     */
    private Date uptime ;
    /**
     * 审核状态：0未审，1审核通过，2审核不通过，3已屏蔽
     */
    private Integer status ;
    /**
     * 审核意见
     */
    private String description ;
    /**
     * 审核人
     */
    private Integer adminId ;
    /**
     * 审核的时间
     */
    private Date adminTime ;
    /**
     * 点赞数量
     */
    private Integer likesNum ;
    /**
     * 转发数量
     */
    private Integer shareNum ;
    /**
     * 评论数量
     */
    private Integer reviewNum ;
}
