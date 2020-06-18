package io.kerns.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询，默认是Integer类型
 *
 * @author xiaohei
 * @create 2020-05-22 上午10:54
 **/
@Data
public class PageIntReq {
    @ApiModelProperty(value = "每页显示的条数，默认20条")
    @JsonProperty(value = "page_size")
    private Integer pageSize=20;
    @ApiModelProperty(value = "页码，默认从0开始")
    @JsonProperty(value = "page_no")
    private Integer pageNo=0;
}
