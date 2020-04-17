package com.xd.elasticsearch.repository.metadata;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohei
 * @create 2020-04-16 下午4:34
 **/
@Data
public class JsonResult {
    private List<Column> columns;

    private List<List<String>> rows;
}
