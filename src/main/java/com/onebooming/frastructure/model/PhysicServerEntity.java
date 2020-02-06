package com.onebooming.frastructure.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;

import java.io.Serializable;
import java.util.HashMap;

/**
 * CPU服务器类
 * @author Onebooming
 *
 */
public class PhysicServerEntity implements Serializable {

    @ExcelIgnore
    private Long id;//设备id
    @Excel(name = "设备名", height = 20, width = 30, isImportField = "true_st")
    private String name;//设备名
    @Excel(name = "设备所在地", height = 20, width = 30, isImportField = "true_st")
    private String area;//设备所在地
    @Excel(name = "数据中心", height = 20, width = 30, isImportField = "true_st")
    private String datacenter;//数据中心
    @Excel(name = "机架位置", height = 20, width = 30, isImportField = "true_st")
    private String position;//位置
    @Excel(name = "高度", height = 20, width = 30, isImportField = "true_st")
    private String height;//高度
    @Excel(name = "品牌", height = 20, width = 30, isImportField = "true_st")
    private String brand;//品牌
    @Excel(name = "型号", height = 20, width = 30, isImportField = "true_st")
    private String type;//型号
    @Excel(name = "序列号", height = 20, width = 30, isImportField = "true_st")
    private String serialNumber;//序列号
    @Excel(name = "业务IP", height = 20, width = 30, isImportField = "true_st")
    private String bIp;//业务IP
    @Excel(name = "管理IP", height = 20, width = 30, isImportField = "true_st")
    private String mgmtIp;//管理IP
    @Excel(name = "CPU名", height = 20, width = 30, isImportField = "true_st")
    private String cpuName;//CPUming
    @Excel(name = "CPU数量", height = 20, width = 30, isImportField = "true_st")
    private int cpuNum;//cpu数量
    private HashMap<Integer, Integer> memoryMap;//内存条规格及数量
    @Excel(name = "内存总量", height = 20, width = 30, isImportField = "true_st")
    //key；内存条规模（4G/8G/16G/32G...），value：内存条数量
    private int memorySum;//内存总量
    @Excel(name = "存储空间总量", height = 20, width = 30, isImportField = "true_st")
    private int storage;//存储空间总量
    private HashMap<String, Integer> diskMap;//磁盘规格及数量
    @Excel(name = "制造商", height = 20, width = 30, isImportField = "true_st")
    private String manufactor;//制造商
    @Excel(name = "所属部门", height = 20, width = 30, isImportField = "true_st")
    private String department;//所属部门
    @Excel(name = "使用者", height = 20, width = 30, isImportField = "true_st")
    private String user;//使用者
    @Excel(name = "运维人员", height = 20, width = 30, isImportField = "true_st")
    private String maintainor;//运维人员

    @Override
    public String toString() {
        return "PhysicServer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", datacenter='" + datacenter + '\'' +
                ", position='" + position + '\'' +
                ", height='" + height + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", bIp='" + bIp + '\'' +
                ", mgmtIp='" + mgmtIp + '\'' +
                ", cpuName='" + cpuName + '\'' +
                ", cpuNum=" + cpuNum +
                ", memoryMap=" + memoryMap +
                ", memorySum=" + memorySum +
                ", storage=" + storage +
                ", diskMap=" + diskMap +
                ", manufactor='" + manufactor + '\'' +
                ", department='" + department + '\'' +
                ", user='" + user + '\'' +
                ", maintainor='" + maintainor + '\'' +
                '}';
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public HashMap<Integer, Integer> getMemoryMap() {
        return memoryMap;
    }

    public void setMemoryMap(HashMap<Integer, Integer> memoryMap) {
        this.memoryMap = memoryMap;
    }

    public int getMemorySum() {
        return memorySum;
    }

    public void setMemorySum(int memorySum) {
        this.memorySum = memorySum;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public HashMap<String, Integer> getDiskMap() {
        return diskMap;
    }

    public void setDiskMap(HashMap<String, Integer> diskMap) {
        this.diskMap = diskMap;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMaintainor() {
        return maintainor;
    }

    public void setMaintainor(String maintainor) {
        this.maintainor = maintainor;
    }


    public PhysicServerEntity() {
    }
}
