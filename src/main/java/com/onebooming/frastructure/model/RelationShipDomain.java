package com.onebooming.frastructure.model;

import java.io.Serializable;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/11 19:29
 * 网络关联信息表
 */
public class RelationShipDomain implements Serializable {

    /**
     * 文章主键编号
     */
    private Integer cid;
    /**
     * 项目编号
     */
    private Integer mid;



    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
