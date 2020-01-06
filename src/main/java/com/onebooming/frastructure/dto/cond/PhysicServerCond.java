package com.onebooming.frastructure.dto.cond;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 服务器查找条件
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/13 15:50
 */
@Component
public class PhysicServerCond implements Serializable {
    private String name;//设备名
    private String area;//设备所在地
    private String datacenter;//数据中心
    private String position;//位置
    private String brand;//品牌
    private String serialNumber;//序列号
    private String bIp;//业务IP
    private String mgmtIp;//管理IP

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getbIp() {
        return bIp;
    }

    public void setbIp(String bIp) {
        this.bIp = bIp;
    }

    public String getMgmtIp() {
        return mgmtIp;
    }

    public void setMgmtIp(String mgmtIp) {
        this.mgmtIp = mgmtIp;
    }
}
