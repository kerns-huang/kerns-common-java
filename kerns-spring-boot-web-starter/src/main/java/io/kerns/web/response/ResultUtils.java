package io.kerns.web.response;

/**
 * 返回结果的辅助类
 */
public class ResultUtils {

    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(1);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static Result success(CodeMsg code, Object obj){
        Result result = new Result();
        result.setStatus(1);
        result.setMsgCode(code.getCode());
        result.setData(obj);
        return result;
    }

    public  static boolean isNull(Result result){
        return result.getData()==null;
    }

    public static boolean isSuccess(Result result){
        return result.getStatus()==1;
    }

    public static Result success() {
        Result result = new Result();
        result.setStatus(1);
        result.setMsg("success");
        return result;
    }

    public static Result success(CodeMsg code){
        Result result = new Result();
        result.setStatus(1);
        result.setMsgCode(code.getCode());
        return result;
    }

    public static PageResult pageSuccess(){
        PageResult result = new PageResult();
        result.setStatus(1);
        result.setMsg("success");
        return result;
    }



    public static Result fail(CodeMsg code) {
        Result result = new Result();
        //返回错码信息
        result.setStatus(Integer.parseInt(code.getCode()));
        result.setMsgCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    public static Result fail(String msgCode){
        Result result = new Result();
        result.setStatus(0);
        result.setMsgCode(msgCode);
        return result;
    }


}
