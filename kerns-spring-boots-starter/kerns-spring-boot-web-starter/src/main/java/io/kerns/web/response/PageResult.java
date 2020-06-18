package io.kerns.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页查询
 *
 * @author xiaohei
 * @create 2020-05-22 上午9:59
 **/
@Data
public class PageResult<T> extends Result {
    @ApiModelProperty(value = "返回的对象")
    private List<T> data;
    @ApiModelProperty(value = "总数据")
    private Long total;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "当前页码")
    private Integer page;
    @ApiModelProperty(value = "每页显示的条数")
    private Integer size;

    public void set(Long total,Integer totalPage,Integer page,Integer size){
        setTotal(total);
        setTotalPage(totalPage);
        setPage(page);
        setSize(size);
    }
}
