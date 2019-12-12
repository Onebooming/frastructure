package com.onebooming.frastructure.utils;
import com.google.gson.Gson;
/**
 * Json转换工具
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 10:56
 */
public class GsonUtils {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object){
        return object==null?null:gson.toJson(object);
    }
}
