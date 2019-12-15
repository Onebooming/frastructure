package com.onebooming.frastructure.dto.cond;

/**
 * 用户查找条件
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 14:00
 */
public class UserCond {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
