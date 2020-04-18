package com.onebooming.frastructure.service.InternetEquipment.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.ErrorConstant;
import com.onebooming.frastructure.dao.InternetEquipmentDao;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.InternetEquipment;
import com.onebooming.frastructure.service.InternetEquipment.InternetEquipmentService;
import com.onebooming.frastructure.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println(pageNum + "|" + pageSize);
        //模糊搜索
        List<InternetEquipment> InternetEquipments = internetEquipmentDao.searchInternetEquipment(param);
        //实现分页
        PageInfo<InternetEquipment> pageInfo = new PageInfo<>(InternetEquipments);
        return pageInfo;
    }


    @Override
    public boolean exportToExcel(InternetEquipment internetEquipment) {
        List<InternetEquipment> internetEquipmentList = internetEquipmentDao.getInternetEquipments();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("网络设备信息", "网络设备"),
                InternetEquipment.class, internetEquipmentList);
        String fileName = "InternetEquipment_";

        return ExcelUtil.exportToExcet(workbook, fileName);
    }

    @Override
    public boolean update(Map<String, String> params) throws ParseException {
        //删除id的多余的引号
        String idValue = params.get("\"id");
        params.remove("\"id");
        params.put("id", idValue);
        //取出administrator后面多出来第一个双引号
        String aValue = params.get("administrator");
        String replace = aValue.replace("\"", "");
        params.put("administrator", replace);

        InternetEquipment internetEquipment = JSON.parseObject(JSON.toJSONString(params), InternetEquipment.class);
        System.out.println(internetEquipment.toString());
        int i = internetEquipmentDao.updateInternetEquipment(internetEquipment);
        return i > 0 ? true : false;
    }
}
