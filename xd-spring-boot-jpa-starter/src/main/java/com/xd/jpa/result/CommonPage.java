package com.xd.jpa.result;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 *
 * @author zebra
 */
@Data
public class CommonPage {

    /**
     * 总条数
     */
    private Long total;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 当前页数
     */
    private Integer current;
    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 查询数据
     */
    private List<?> data;

    public CommonPage(Page<?> page) {
        if (null == page) {
            return;
        }
        this.total = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.data = page.getContent();
    }

    public CommonPage() {
        this.total = 0L;
        this.pages = 0;
        this.pageSize = 0;
        this.data = null;
    }
}
