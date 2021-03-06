package com.onebooming.frastructure.dto;

import java.io.Serializable;

/**
 * 公共属性的类
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 13:54
 */
public class BaseDto implements Serializable {

    /** 用户名 */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
