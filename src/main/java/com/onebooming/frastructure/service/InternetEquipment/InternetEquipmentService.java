package com.onebooming.frastructure.service.InternetEquipment;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.dto.cond.InternetEquipmentCond;
import com.onebooming.frastructure.model.InternetEquipment;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Onebooming
 * @Date 2020/4/7
 * @Version V1.0
 **/
public interface InternetEquipmentService {
    PageInfo<InternetEquipment> getInternetEquipments(int pageNum, int pageSize);

    void deleteById(Long id);

    void updateInternetEquipment(InternetEquipment InternetEquipment);

    InternetEquipment findInternetEquipmentById(Long id);

    PageInfo<InternetEquipment> getInternetEquipmentByParamPage(String param, int pageNum, int pageSize);


    boolean exportToExcel(InternetEquipmentCond InternetEquipmentCond);

    void save(Map<String, String> params) throws ParseException;
}
