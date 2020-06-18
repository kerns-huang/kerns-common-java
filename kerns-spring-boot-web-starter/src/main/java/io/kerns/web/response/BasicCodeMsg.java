package io.kerns.web.response;

public enum BasicCodeMsg implements CodeMsg {
    PARAM_ERROR("1004", "參數錯誤"),

    UN_LOGIN("1001", "未登录"),

    AUTH_FAILED("14005", "參數appkey不正確"),

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
