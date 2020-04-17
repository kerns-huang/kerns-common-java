package com.xd.elasticsearch.core.enums;

import lombok.Getter;

@Getter
public enum OrderType {

    DESC("desc"),
    ESC("esc");
    private String value;

    private OrderType(String value){
        this.value=value;
    }
}
