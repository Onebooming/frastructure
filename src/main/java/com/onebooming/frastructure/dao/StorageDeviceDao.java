package com.onebooming.frastructure.dao;

import com.onebooming.frastructure.model.StorageDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-19 15:37
 * @ApiNote
 */
@Mapper
@Component
public interface StorageDeviceDao {
    /**
     * 添加存储设备
     * @param storageDevice
     * @return
     */
    int addStorageDevice(StorageDevice storageDevice);

    /**
     * 根据编号删除存储设备
     * @param id
     * @return
     */
    int deleteStorageDevice(@Param("id") Long id);

    /**
     * 根据存储设备名称删除
     * @param name
     * @return
     */
    int deleteStorageDeviceByName(@Param("name") String name);

    /**
     * 更新存储设备信息
     * @param storageDevice
     * @return
     */
    int updateStorageDevice(StorageDevice storageDevice);


    /**
     * 根据id获取存储设备
     * @param id
     * @return
     */
    StorageDevice getStorageDeviceById(@Param("id") Long id);

    /**
     * 根据条件获取存储设备列表
     * @param storageDeviceDao
     * @return
     */
    //List<StorageDevice> getStorageDeviceByCond(StorageDeviceDao storageDeviceDao);

    /**
     * 获取服务器总数量
     * @return
     */
    Long getStorageDeviceCount();


    /**
     * 搜索存储设备-根据名称 或 信息匹配
     * @param param
     * @return
     */
    List<StorageDevice> searchStorageDevice(@Param("param") String param);

    /**
     * 获取全部数据
     * @return
     */
    List<StorageDevice> getAllStorageDevices();
}
