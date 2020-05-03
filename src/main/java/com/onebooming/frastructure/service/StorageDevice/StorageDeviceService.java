package com.onebooming.frastructure.service.StorageDevice;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.model.StorageDevice;

import java.text.ParseException;
import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-23 21:26
 * @ApiNote
 */
public interface StorageDeviceService {
    PageInfo<StorageDevice> getStorageDevice(int pageNum, int pageSize);
    void deletById(Long id);
    void updateStorageDevice(StorageDevice storageDevice);
    StorageDevice findStorageDeviceById(Long id);
    PageInfo<StorageDevice> getStorageDeviceByParamPage(String param,int pageNum,int pageSize);
    boolean exportToExcel(StorageDevice storageDevice);
    boolean update(Map<String,String> params) throws ParseException;
}
