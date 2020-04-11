package com.onebooming.frastructure.service.InternetEquipment.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.ErrorConstant;
import com.onebooming.frastructure.dao.InternetEquipmentDao;
import com.onebooming.frastructure.dto.cond.InternetEquipmentCond;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.InternetEquipment;
import com.onebooming.frastructure.service.InternetEquipment.InternetEquipmentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/7
 * @Version V1.0
 **/
@Service
public class InternetEquipmentServiceImpl implements InternetEquipmentService {
    @Autowired
    InternetEquipmentDao internetEquipmentDao;

    @Override
    public PageInfo<InternetEquipment> getInternetEquipments(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InternetEquipment> internetEquipmentList = internetEquipmentDao.getInternetEquipments();
        PageInfo<InternetEquipment> pageInfo = new PageInfo<>(internetEquipmentList);
        return pageInfo;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        internetEquipmentDao.deleteInternetEquipment(id);

    }

    @Override
    public void updateInternetEquipment(InternetEquipment InternetEquipment) {
        internetEquipmentDao.updateInternetEquipment(InternetEquipment);
    }

    @Override
    public InternetEquipment findInternetEquipmentById(Long id) {
        if (id == null) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return internetEquipmentDao.getInternetEquipmentById(id);
    }

    @Override
    public PageInfo<InternetEquipment> getInternetEquipmentByParamPage(String param, int pageNum, int pageSize) {
        if (null == param)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        System.out.println(param);
        PageHelper.startPage(pageNum, pageSize);
        System.out.println(pageNum+"|"+pageSize);
        //模糊搜索
        List<InternetEquipment> InternetEquipments = internetEquipmentDao.searchInternetEquipment(param);
        //实现分页
        PageInfo<InternetEquipment> pageInfo = new PageInfo<>(InternetEquipments);
        return pageInfo;
    }



    @Override
    public boolean exportToExcel(InternetEquipmentCond InternetEquipmentCond) {
        List<InternetEquipment> internetEquipmentList = internetEquipmentDao.getInternetEquipments();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("网络设备信息", "网络设备"),
                InternetEquipment.class, internetEquipmentList);
        String fileName = "InternetEquipment_";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String savePath =  fileName + sdf.format(new Date()) + ".xls";
        workbook.setSheetName(0, fileName);
        workbook.getSheetAt(0).setDefaultRowHeight((short)21);
        String localPathPrefix = "file";

        try {
            File filePath = new File(localPathPrefix);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            File localFile = new File(localPathPrefix + File.separator + savePath);
            OutputStream os = new FileOutputStream(localFile);

            workbook.write(os);
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void save(Map<String, String> params) throws ParseException {
        InternetEquipment ie = new InternetEquipment();
        ie.setName(params.get("name"));
        ie.setRole(params.get("role"));
        ie.setMgmtIp(params.get("mgmtIp"));
        ie.setAssetStatus(params.get("assetStatus"));
        ie.setDescription(params.get("description"));
        ie.setCity(params.get("city"));
        ie.setArea(params.get("area"));
        ie.setPosition(params.get("position"));
        ie.setRackPosition(params.get("rackPosition"));
        ie.setBrand(params.get("brand"));
        ie.setType(params.get("type"));
        ie.setOSVersion(params.get("OSVersion"));
        ie.setCPUFrequency(Float.valueOf(params.get("CPUFrequency")));
        ie.setMemory(Integer.valueOf(params.get("memory")));
        ie.setSerialNumber(params.get("serialNumber"));
        ie.setPowerSuppliesNumber(Integer.valueOf(params.get("powerSuppliesNumber")));
        ie.setFanNumber(Integer.valueOf(params.get("fanNumber")));
        ie.setLicenseTerm(params.get("licenseTerm"));
        ie.setMonitor(params.get("monitor"));
        ie.setPortSpeed(Float.valueOf(params.get("portSpeed")));
        ie.setPortType(params.get("portType"));
        ie.setPortNumber(Integer.valueOf(params.get("portNumber")));
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(params.get("listDate"));
        ie.setListDate(date);
        date = sdf.parse(params.get("warrantyPeriod"));
        ie.setWarrantyPeriod(date);
        ie.setManufacturer(params.get("manufacturer"));
        ie.setAgent(params.get("agent"));
        ie.setMaintenanceManufacturers(params.get("maintenanceManufacturers"));
        ie.setOMDepartment(params.get("OMDepartment"));
        ie.setAdministrator(params.get("administrator"));
        internetEquipmentDao.addInternetEquipment(ie);
    }
}
