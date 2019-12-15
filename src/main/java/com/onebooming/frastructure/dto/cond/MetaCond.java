package com.onebooming.frastructure.dto.cond;

/**
 * meta查询条件
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 14:00
 */
public class MetaCond {


    /**
     * meta Name
     */
    private String name;
    /**
     * 类型
     */
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
