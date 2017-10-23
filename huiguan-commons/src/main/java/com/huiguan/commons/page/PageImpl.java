/*
 * huiguan.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.huiguan.commons.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * @author huangchunbo
 * @since $$Revision:1.0.0, $$Date: 2017/9/14 21:34 $$
 */
public class PageImpl<T extends Serializable> implements Serializable {
    private Integer total;

    private Integer totalPages;

    private Integer currentPage;

    private Integer pageNum;

    private List<T> datas;

    public PageImpl(List<T> list, Integer currentPage, Integer total, Integer pageNum) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (total == null) {
            total = 0;
        }
        if (pageNum == null) {
            pageNum = 20;
        }
        this.datas = list;
        this.pageNum = pageNum;
        this.currentPage = currentPage;
        this.total = total;
        Integer totalPage = Math.toIntExact(this.total / pageNum);
        this.totalPages = this.total % pageNum == 0 ? totalPage : (totalPage + 1);

    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
