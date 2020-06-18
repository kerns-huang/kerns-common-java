package io.kerns.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Result<T> {
    @ApiModelProperty(value = "返回的状态")
    private Integer status;
    @ApiModelProperty(value = "返回的消息")
    private String msg;
    @ApiModelProperty(value = "返回的消息code")
    private String msgCode;
    @ApiModelProperty(value = "返回的对象")
    private T data;
    @ApiModelProperty(value = "当前时间，以秒为单位")
    private Long current=System.currentTimeMillis()/1000;



}
