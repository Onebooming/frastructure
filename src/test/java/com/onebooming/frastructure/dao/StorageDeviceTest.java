package com.onebooming.frastructure.dao;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.onebooming.frastructure.dto.StorageDeviceDto;
import com.onebooming.frastructure.model.StorageDevice;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-19 15:44
 * @ApiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageDeviceTest {
    @Autowired
    StorageDeviceDao storageDeviceDao;

    @Autowired
    PhysicServerDao physicServerDao;
    @Test
    public void test(){
        List<String> li = physicServerDao.searchSwitchNameList("B9企业_B5_02U_C240 M4SX");
        for (String s : li) {
            System.out.println(s);
        }
    }

    /**
     * 测试
     */
    @Test
    public void excelImportTest() throws Exception {
        String filepath = "/Users/chenglei/Downloads/已导入系统（以它为准）/整理/storage.xlsx";//C盘下的file文件夹的目录
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<StorageDeviceDto> list = ExcelImportUtil.importExcel(
                new File("/Users/chenglei/Downloads/已导入系统（以它为准）/整理/storage.xlsx"),
                StorageDeviceDto.class, params);
        System.out.println(list.size());
        for (StorageDeviceDto storageDeviceDto : list) {
            //storageDeviceDao.addStorageDevice(storageDeviceDto);
            String s = storageDeviceDto.toString();
            System.out.println(s);
        }



    }


    @Test
    public void internetEquipmentImport() throws Exception {
        String filepath = "/Users/chenglei/Downloads/已导入系统（以它为准）/整理/storage.xlsx";//C盘下的file文件夹的目录
        File newFile = new File(filepath);//File类型可以是文件也可以是文件夹
        FileInputStream fileInputStream  =new FileInputStream(newFile);

        MultipartFile multipartFile = new MockMultipartFile(newFile.getName(), newFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        ImportParams importParams = new ImportParams();
        // 因为Excel是从第3行开始才有正式数据,所以我们这里从第4行开始读取数据
        importParams.setHeadRows(1);



        // 获取模板
        ExcelImportResult<StorageDeviceDto> data = null;
        try {
            data = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), StorageDeviceDto.class, importParams);
        } catch (Exception e) {

        }

        // 获取模板数据
        List<StorageDeviceDto> successList = data.getList();

        int total = successList.size();
        if (total == 0) {
            // 如果数据量为0直接返回错误信息,不继续执行

            System.out.println("数量为0，未读取到数据");
        }

        for (StorageDeviceDto dto : successList) {
            System.out.println("读取到数据了");
            System.out.println(dto.toString());
            //storageDeviceDao.addStorageDevice(dto);

        }

    }

    @Test
    public void test2(){
        List<StorageDevice> allStorageDevices = storageDeviceDao.getAllStorageDevices();
        System.out.println(allStorageDevices.size());
        StorageDevice storageDevice = new StorageDevice();
        storageDevice.setName("chenglei");
//        storageDeviceDao.addStorageDevice(storageDevice);
//        storageDeviceDao.deleteStorageDevice(82L);
//        storageDeviceDao.deleteStorageDeviceByName("chenglei");
        storageDevice.setId(84L);
        storageDevice.setName("liming");
        storageDeviceDao.updateStorageDevice(storageDevice);
        List<StorageDevice> b4 = storageDeviceDao.searchStorageDevice("B4");
        System.out.println(b4.size());

    }

}
