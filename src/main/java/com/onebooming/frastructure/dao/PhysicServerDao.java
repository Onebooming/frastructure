package com.onebooming.frastructure.dao;

import com.onebooming.frastructure.dto.ArchiveDto;
import com.onebooming.frastructure.dto.cond.ContentCond;
import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.model.ContentDomain;
import com.onebooming.frastructure.model.PhysicServerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/13 15:42
 */
@Mapper
public interface PhysicServerDao {
    /**
     * 添加服务器
     * @param physicServerEntity
     * @return
     */
    int addPhysicServer(PhysicServerEntity physicServerEntity);

    /**
     * 根据编号删除服务器
     * @param id
     * @return
     */
    int deletePhysicServerById(@Param("id") Long id);

    /**
     * 根据服务器名删除服务器
     * @param name
     * @return
     */
    int deletePhysicServerByName(@Param("name") String name);

    /**
     * 更新服务器信息
     * @param physicServerEntity
     * @return
     */
    int updatePhysicServer(PhysicServerEntity physicServerEntity);


    /**
     * 根据id获取服务器
     * @param id
     * @return
     */
    PhysicServerEntity getPhysicServerById(@Param("id") Long id);

    /**
     * 根据条件获取服务器列表
     * @param physicServerCond
     * @return
     */
    List<PhysicServerEntity> getPhysicServersByCond(PhysicServerCond physicServerCond);

    /**
     * 获取服务器总数量
     * @return
     */
    Long getPhysicServerCount();


    /**
     * 搜索服务器-根据名称 或 信息匹配
     * @param param
     * @return
     */
    List<PhysicServerEntity> searchPhysicServer(@Param("param") String param);

}
