package com.onebooming.frastructure.dao;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.onebooming.frastructure.dto.InternetEquipmentDto;
import com.onebooming.frastructure.model.ConvertUtil;
import com.onebooming.frastructure.model.ExcelDemo;
import com.onebooming.frastructure.model.InternetEquipment;
import io.swagger.models.auth.In;
import org.apache.http.entity.ContentType;
import org.apache.poi.ss.usermodel.Workbook;
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
 * @date 2020-04-18 15:48
 * @ApiNote
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetEquipmentTest {
    @Autowired
    InternetEquipmentDao internetEquipmentDao;

    @Test
    public void searchInternetEquipment(){
        Long count =  internetEquipmentDao.getInternetEquipmentCount();
        System.out.println(count);
    }


    /**
     * 网络设备
     */
    @Test
    public void internetEquipmentImport() throws Exception {
        String filepath = "C:\\Users\\10224683\\Desktop\\CMDB\\test.xlsx";//C盘下的file文件夹的目录
        File newFile = new File(filepath);//File类型可以是文件也可以是文件夹
        FileInputStream fileInputStream  =new FileInputStream(newFile);

        MultipartFile multipartFile = new MockMultipartFile(newFile.getName(), newFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        ImportParams importParams = new ImportParams();
        // 因为Excel是从第3行开始才有正式数据,所以我们这里从第4行开始读取数据
        importParams.setHeadRows(1);



        // 获取模板
        ExcelImportResult<InternetEquipmentDto> data = null;
        try {
            data = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), InternetEquipmentDto.class, importParams);
        } catch (Exception e) {

        }

        // 获取模板数据
        List<InternetEquipmentDto> successList = data.getList();

        int total = successList.size();
        if (total == 0) {
            // 如果数据量为0直接返回错误信息,不继续执行

            System.out.println("数量为0，未读取到数据");
        }

        for (InternetEquipmentDto dto : successList) {
            System.out.println("读取到数据了");
            System.out.println(dto);
            InternetEquipment internetEquipment = ConvertUtil.convertToInternetEquipment(dto);
            System.out.println(internetEquipment);
            internetEquipmentDao.addInternetEquipment(internetEquipment);

        }

    }

    /**
     * 测试
     */
    @Test
    public void excelImportTest() throws Exception {
        String filepath = "C:\\Users\\10224683\\Desktop\\CMDB\\test.xlsx";//C盘下的file文件夹的目录
        File newFile = new File(filepath);//File类型可以是文件也可以是文件夹
        FileInputStream fileInputStream  =new FileInputStream(newFile);

        MultipartFile multipartFile = new MockMultipartFile(newFile.getName(), newFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//        String multipartFile = Thread.currentThread().getContextClassLoader().getResource("C:\\Users\\10224683\\Desktop\\CMDB\\test.xlsx").getFile();

        ImportParams importParams = new ImportParams();
        // 因为Excel是从第3行开始才有正式数据,所以我们这里从第4行开始读取数据
        importParams.setHeadRows(1);



        // 获取模板
        ExcelImportResult<ExcelDemo> data = null;
        try {

            data = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), ExcelDemo.class, importParams);
//            data = ExcelImportUtil.importExcelMore(new File(multipartFile), ExcelDemo.class, importParams);
        } catch (Exception e) {

        }

        // 获取模板数据
        List<ExcelDemo> successList = data.getList();

        int total = successList.size();
        if (total == 0) {
            // 如果数据量为0直接返回错误信息,不继续执行

            System.out.println("数量为0，未读取到数据");
        }

        for (ExcelDemo excelDemo : successList) {
            System.out.println("读取到数据了");
            System.out.println(excelDemo);

        }

    }

}

