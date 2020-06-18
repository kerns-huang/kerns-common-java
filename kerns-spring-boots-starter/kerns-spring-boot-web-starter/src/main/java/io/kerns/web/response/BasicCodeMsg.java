package io.kerns.web.response;

/**
 * 基本的错误信息
 */
public enum BasicCodeMsg implements CodeMsg {
    PARAM_ERROR("1004", "參數錯誤"),

    UN_LOGIN("1001", "未登录"),


    ;
    private String code;
    private String msg;

    private BasicCodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }
}
