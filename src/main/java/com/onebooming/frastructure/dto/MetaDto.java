package com.onebooming.frastructure.dto;

import com.onebooming.frastructure.model.MetaDomain;

/**
 * 标签、分类列表
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 13:56
 */
public class MetaDto extends MetaDomain {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
