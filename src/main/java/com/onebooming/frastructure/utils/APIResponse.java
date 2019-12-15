package com.onebooming.frastructure.utils;

/**
 * 返回参数封装类
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 10:40
 */
public class APIResponse <T> {
    private static final String CODE_SUCCESS = "success";
    private static final String CODE_FAIL = "fail";

    private String code;
    private T data;
    private String msg;

    public APIResponse(){

    }

    public APIResponse(String code){
        this.code = code;
    }

    public APIResponse(String code, T data){
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回成功状态
     * @return
     */
    public static APIResponse success(){
        return new APIResponse(CODE_SUCCESS);
    }

    /**
     * 返回成功状态+数据
     * @param data
     * @return
     */
    public static APIResponse success(Object data){
        return new APIResponse(CODE_SUCCESS, data);
    }

    /**
     * 返回失败状态+消息
     * @param msg
     * @return
     */
    public static APIResponse fail(String msg){
        return new APIResponse(CODE_FAIL, msg);
    }

    /**
     * 返回其他错误码
     * @param errorCode
     * @return
     */
    public static APIResponse widthCode(String errorCode) {
        return new APIResponse(errorCode);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
