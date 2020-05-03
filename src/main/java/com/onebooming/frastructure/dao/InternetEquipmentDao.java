package com.onebooming.frastructure.dao;


import com.onebooming.frastructure.model.InternetEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Onebooming
 * @Dao 接口
 */
@Component
@Mapper
public interface InternetEquipmentDao {
    /**
     * 添加网络设备
     * @param internetEquipment
     * @return
     */
    int addInternetEquipment(InternetEquipment internetEquipment);

    /**
     * 根据编号删除网络设备
     * @param id
     * @return
     */
    int deleteInternetEquipment(@Param("id") Long id);

    /**
     * 根据网络设备名称删除服务器
     * @param name
     * @return
     */
    int deleteInternetEquipmentByName(@Param("name") String name);

    /**
     * 更新网络设备信息
     * @param internetEquipment
     * @return
     */
    int updateInternetEquipment(InternetEquipment internetEquipment);


    /**
     * 根据id获取网络设备
     * @param id
     * @return
     */
    InternetEquipment getInternetEquipmentById(@Param("id") Long id);

    /**
     * 根据条件获取网络设备列表
     * @param internetEquipmentDao
     * @return
     */
    //List<InternetEquipment> getInternetEquipmentByCond(InternetEquipmentDao internetEquipmentDao);

    /**
     * 获取网络设备总数量
     * @return
     */
    Long getInternetEquipmentCount();


    /**
     * 搜索网络-根据名称 或 信息匹配
     * @param param
     * @return
     */
    List<InternetEquipment> searchInternetEquipment(@Param("param") String param);

    /**
     * 获取全部数据
     * @return
     */
    List<InternetEquipment> getInternetEquipments();
}
