package com.onebooming.frastructure.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-18 16:17
 * @ApiNote
 */
public class StorageDeviceDto implements java.io.Serializable {
    //    序号
    @Excel(name = "序号",isImportField = "true_st")
    private Long id;
    //    设备ID
    @Excel(name = "设备ID",isImportField = "true_st")
    private String name;
    //    型号
    @Excel(name = "型号",isImportField = "true_st")
    private String type;
    //    管理IP
    @Excel(name = "管理IP",isImportField = "true_st")
    private String mgmtIp;
    //    业务IP
    @Excel(name = "业务IP",isImportField = "true_st")
    private String bIp;
    //    地区
    @Excel(name = "地区",isImportField = "true_st")
    private String city;
    //    地点
    @Excel(name = "地点",isImportField = "true_st")
    private String area;
    //    位置
    @Excel(name = "位置",isImportField = "true_st")
    private String position;
    //    用途
    @Excel(name = "用途",isImportField = "true_st")
    private String usage;
    //    品牌
    @Excel(name = "品牌",isImportField = "true_st")
    private String brand;
    //    序列号
    @Excel(name = "序列号",isImportField = "true_st")
    private String serialNumber;
    //    设备角色
    @Excel(name = "设备角色",isImportField = "true_st")
    private String role;
    //    HBA-FC卡端口数
    @Excel(name = "HBA-FC卡端口数",isImportField = "true_st")
    private String fcPortsNumber;
    //    HBA-FC卡速率
    @Excel(name = "HBA-FC卡速率",isImportField = "true_st")
    private String fcPortsVelocity;
    //    HBA-IP卡端口数
    @Excel(name = "HBA-IP卡端口数",isImportField = "true_st")
    private String ipPortsNumber;
    //    HBA-IP卡速率
    @Excel(name = "HBA-IP卡速率",isImportField = "true_st")
    private String ipPortsVelocity;
    //    SAS磁盘大小
    @Excel(name = "SAS磁盘大小",isImportField = "true_st")
    private String SASspecification;
    //    SAS磁盘数量/个
    @Excel(name = "SAS磁盘数量/个",isImportField = "true_st")
    private String SASnumber;
    //    SAS磁盘总容量
    @Excel(name = "SAS磁盘总容量",isImportField = "true_st")
    private String SAScapacity;
    //    SATA磁盘大小
    @Excel(name = "SATA磁盘大小",isImportField = "true_st")
    private String SATAspecification;
    //    SATA磁盘数量/个
    @Excel(name = "SATA磁盘数量/个",isImportField = "true_st")
    private String SATAnumber;
    //    SATA磁盘总容量
    @Excel(name = "SATA磁盘总容量",isImportField = "true_st")
    private String SATAcapacity;
    //    SSD磁盘大小
    @Excel(name = "SSD磁盘大小",isImportField = "true_st")
    private String SSDspecification;
    //    SSD磁盘数量/个
    @Excel(name = "SSD磁盘数量/个",isImportField = "true_st")
    private String SSDnumber;
    //    SSD磁盘总容量
    @Excel(name = "SSD磁盘总容量",isImportField = "true_st")
    private String SSDcapacity;

    //    资产状态
    @Excel(name = "资产状态",isImportField = "true_st")
    private String assetStatus;
    //    描述
    @Excel(name = "描述",isImportField = "true_st")
    private String description;
    //    上架日期
    @Excel(name = "上架日期",isImportField = "true_st",importFormat = "yyyy/MM/dd")
    private Date listDate;
    //    维保期限
    @Excel(name = "维保期限",isImportField = "true_st", importFormat  = "yyyy/MM/dd")
    private Date warrantyPeriod;
    //    制造商
    @Excel(name = "制造商",isImportField = "true_st")
    private String manufacturer;
    //    维保厂商
    @Excel(name = "维保厂商",isImportField = "true_st")
    private String maintenanceManufacturers;
    //    代理商
    @Excel(name = "代理商",isImportField = "true_st")
    private String agent;
    //    运维部门
    @Excel(name = "运维部门",isImportField = "true_st")
    private String OMDepartment;
    //    硬件管理员
    @Excel(name = "硬件管理员",isImportField = "true_st")
    private String administrator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMgmtIp() {
        return mgmtIp;
    }

    public void setMgmtIp(String mgmtIp) {
        this.mgmtIp = mgmtIp;
    }

    public String getbIp() {
        return bIp;
    }

    public void setbIp(String bIp) {
        this.bIp = bIp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFcPortsNumber() {
        return fcPortsNumber;
    }

    public void setFcPortsNumber(String fcPortsNumber) {
        this.fcPortsNumber = fcPortsNumber;
    }

    public String getFcPortsVelocity() {
        return fcPortsVelocity;
    }

    public void setFcPortsVelocity(String fcPortsVelocity) {
        this.fcPortsVelocity = fcPortsVelocity;
    }

    public String getIpPortsNumber() {
        return ipPortsNumber;
    }

    public void setIpPortsNumber(String ipPortsNumber) {
        this.ipPortsNumber = ipPortsNumber;
    }

    public String getIpPortsVelocity() {
        return ipPortsVelocity;
    }

    public void setIpPortsVelocity(String ipPortsVelocity) {
        this.ipPortsVelocity = ipPortsVelocity;
    }

    public String getSASspecification() {
        return SASspecification;
    }

    public void setSASspecification(String SASspecification) {
        this.SASspecification = SASspecification;
    }

    public String getSASnumber() {
        return SASnumber;
    }

    public void setSASnumber(String SASnumber) {
        this.SASnumber = SASnumber;
    }

    public String getSAScapacity() {
        return SAScapacity;
    }

    public void setSAScapacity(String SAScapacity) {
        this.SAScapacity = SAScapacity;
    }

    public String getSATAspecification() {
        return SATAspecification;
    }

    public void setSATAspecification(String SATAspecification) {
        this.SATAspecification = SATAspecification;
    }

    public String getSATAnumber() {
        return SATAnumber;
    }

    public void setSATAnumber(String SATAnumber) {
        this.SATAnumber = SATAnumber;
    }

    public String getSATAcapacity() {
        return SATAcapacity;
    }

    public void setSATAcapacity(String SATAcapacity) {
        this.SATAcapacity = SATAcapacity;
    }

    public String getSSDspecification() {
        return SSDspecification;
    }

    public void setSSDspecification(String SSDspecification) {
        this.SSDspecification = SSDspecification;
    }

    public String getSSDnumber() {
        return SSDnumber;
    }

    public void setSSDnumber(String SSDnumber) {
        this.SSDnumber = SSDnumber;
    }

    public String getSSDcapacity() {
        return SSDcapacity;
    }

    public void setSSDcapacity(String SSDcapacity) {
        this.SSDcapacity = SSDcapacity;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public Date getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Date warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMaintenanceManufacturers() {
        return maintenanceManufacturers;
    }

    public void setMaintenanceManufacturers(String maintenanceManufacturers) {
        this.maintenanceManufacturers = maintenanceManufacturers;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getOMDepartment() {
        return OMDepartment;
    }

    public void setOMDepartment(String OMDepartment) {
        this.OMDepartment = OMDepartment;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public static void main(String[] args) {

        String s = "id,name,type,mgmtIp,bIp,city,area,position,usage,brand,serialNumber,role,fcPortsNumber," +
                "fcPortsVelocity,ipPortsNumber,ipPortsVelocity,SASspecification,SASnumber,SAScapacity"
                +",SATAspecification,SATAnumber,SATAcapacity,SSDspecification,SSDnumber,SSDcapacity,assetStatus,description,listDate,warrantyPeriod,manufacturer,maintenanceManufacturers," +
                "agent,OMDepartment,administrator";
        String[] split = s.split(",");
        StringBuilder sb = new StringBuilder();
        System.out.println(split.length);
        for (String s1 : split) {
            sb.append("#{").append(s1).append(", jdbcType=VARCHAR},");
            // #{name, jdbcType=VARCHAR},
        }
        System.out.println(sb.toString());

    }

    @Override
    public String toString() {
        return "StorageDeviceDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", mgmtIp='" + mgmtIp + '\'' +
                ", bIp='" + bIp + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", position='" + position + '\'' +
                ", usage='" + usage + '\'' +
                ", brand='" + brand + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", role='" + role + '\'' +
                ", fcPortsNumber='" + fcPortsNumber + '\'' +
                ", fcPortsVelocity='" + fcPortsVelocity + '\'' +
                ", ipPortsNumber='" + ipPortsNumber + '\'' +
                ", ipPortsVelocity='" + ipPortsVelocity + '\'' +
                ", SASspecification='" + SASspecification + '\'' +
                ", SASnumber='" + SASnumber + '\'' +
                ", SAScapacity='" + SAScapacity + '\'' +
                ", SATAspecification='" + SATAspecification + '\'' +
                ", SATAnumber='" + SATAnumber + '\'' +
                ", SATAcapacity='" + SATAcapacity + '\'' +
                ", SSDspecification='" + SSDspecification + '\'' +
                ", SSDnumber='" + SSDnumber + '\'' +
                ", SSDcapacity='" + SSDcapacity + '\'' +
                ", assetStatus='" + assetStatus + '\'' +
                ", description='" + description + '\'' +
                ", listDate=" + listDate +
                ", warrantyPeriod=" + warrantyPeriod +
                ", manufacturer='" + manufacturer + '\'' +
                ", maintenanceManufacturers='" + maintenanceManufacturers + '\'' +
                ", agent='" + agent + '\'' +
                ", OMDepartment='" + OMDepartment + '\'' +
                ", administrator='" + administrator + '\'' +
                '}';
    }
}
