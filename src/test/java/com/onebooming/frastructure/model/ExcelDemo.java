package com.onebooming.frastructure.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/7
 * @Version V1.0
 **/
public class ExcelDemo implements Serializable {
    //序号
    @Excel(name = "序号")
    private Long id;
    //设备名-唯一
    @Excel(name = "设备ID")
    private String name;

    //设备角色
    @Excel(name = "设备角色")
    private String role;
    //管理IP
    @Excel(name = "管理IP")
    private String mgmtIp;
    //资产状态
    @Excel(name = "资产状态")
    private String assetStatus;

    //描述
    @Excel(name = "描述")
    private String description;
    //地区
    @Excel(name = "地区")
    private String city;
    //地点
    @Excel(name = "地点")
    private String area;
    //位置
    @Excel(name = "位置")
    private String position;
    //高度/U
    @Excel(name = "高度")
    private String rackPosition;
    //品牌
    @Excel(name = "品牌")
    private String brand;
    //型号
    @Excel(name = "型号")
    private String type;
    //系统版本
    @Excel(name = "系统版本")
    private String OSVersion;
    @Excel(name = "CPU主频")
    private Float CPUFrequency;

    //内存容量
    @Excel(name = "内存容量")
    private Integer memory;
    //序列号
    @Excel(name = "序列号")
    private String serialNumber;
    //电源数目
    @Excel(name = "电源数目")
    private Integer powerSuppliesNumber;
    //风扇数目
    @Excel(name = "风扇数目")
    private Integer fanNumber;
    //license期限
    @Excel(name = "license期限")
    private String licenseTerm;
    //是否监控
    @Excel(name = "是否监控")
    private String monitor;
    //端口速率
    @Excel(name = "端口速率")
    private Float portSpeed;
    //端口类型
    @Excel(name = "端口类型")
    private String portType;
    //端口数量
    @Excel(name = "端口数量")
    private Integer portNumber;
    @Excel(name = "上架日期" , importFormat = "yyyy/MM/dd")
    private Date listDate;

    //    @Excel()
//    //上架日期
//    @Excel(name = "日期",importFormat = "yyyy/MM/dd")
//    private Date ListDate;
    //维保期限
    @Excel(name = "维保期限" , importFormat = "yyyy/MM/dd")
    private Date warrantyPeriod;

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
    //制造商
    @Excel(name = "制造商")
    private String manufacturer;
    //代理商
    @Excel(name = "代理商")
    private String agent;
    //维保厂商
    @Excel(name = "维保厂商")
    private String maintenanceManufacturers;
    //运维部门
    @Excel(name = "运维部门")
    private String OMDepartment;
    //运维管理员
    @Excel(name = "运维管理员")
    private String administrator;

    public Float getCPUFrequency() {
        return CPUFrequency;
    }

    public void setCPUFrequency(Float CPUFrequency) {
        this.CPUFrequency = CPUFrequency;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMgmtIp() {
        return mgmtIp;
    }

    public void setMgmtIp(String mgmtIp) {
        this.mgmtIp = mgmtIp;
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

    public String getRackPosition() {
        return rackPosition;
    }

    public void setRackPosition(String rackPosition) {
        this.rackPosition = rackPosition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPowerSuppliesNumber() {
        return powerSuppliesNumber;
    }

    public void setPowerSuppliesNumber(Integer powerSuppliesNumber) {
        this.powerSuppliesNumber = powerSuppliesNumber;
    }

    public Integer getFanNumber() {
        return fanNumber;
    }

    public void setFanNumber(Integer fanNumber) {
        this.fanNumber = fanNumber;
    }

    public String getLicenseTerm() {
        return licenseTerm;
    }

    public void setLicenseTerm(String licenseTerm) {
        this.licenseTerm = licenseTerm;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public Float getPortSpeed() {
        return portSpeed;
    }

    public void setPortSpeed(Float portSpeed) {
        this.portSpeed = portSpeed;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

//    public Date getListDate() {
//        return ListDate;
//    }
//
//    public void setListDate(Date listDate) {
//        ListDate = listDate;
//    }

//    public Date getWarrantyPeriod() {
//        return warrantyPeriod;
//    }
//
//    public void setWarrantyPeriod(Date warrantyPeriod) {
//        this.warrantyPeriod = warrantyPeriod;
//    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getMaintenanceManufacturers() {
        return maintenanceManufacturers;
    }

    public void setMaintenanceManufacturers(String maintenanceManufacturers) {
        this.maintenanceManufacturers = maintenanceManufacturers;
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


    @Override
    public String toString() {
        return "ExcelDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", mgmtIp='" + mgmtIp + '\'' +
                ", assetStatus='" + assetStatus + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", position='" + position + '\'' +
                ", rackPosition='" + rackPosition + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", OSVersion='" + OSVersion + '\'' +
                ", CPUFrequency=" + CPUFrequency +
                ", memory=" + memory +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}