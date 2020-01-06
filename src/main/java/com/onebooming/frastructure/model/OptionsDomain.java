package com.onebooming.frastructure.model;

import java.io.Serializable;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/11 19:28
 * 网站配置项
 */
public class OptionsDomain implements Serializable {
    /** 名称 */
    private String name;
    /** 内容 */
    private String value;
    /** 备注 */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
