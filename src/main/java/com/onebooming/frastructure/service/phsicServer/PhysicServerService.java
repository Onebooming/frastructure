package com.onebooming.frastructure.service.phsicServer;

import com.github.pagehelper.PageInfo;

import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.model.PhysicServerEntity;
import org.springframework.stereotype.Service;

/**
 * 服务器服务层
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/13 16:57
 */

public interface PhysicServerService {
    /**
     * 根据条件获取文章列表
     * @param physicServerCond
     * @return
     */
    PageInfo<PhysicServerEntity> getPhysicServerByCond(PhysicServerCond physicServerCond, int pageNum, int pageSize);

    /**
     * 添加缓存功能后的根据条件获取文章列表
     * @param physicServerCond
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PhysicServerEntity> getPhysicServerByCond2(PhysicServerCond physicServerCond, int pageNum, int pageSize);

    void deleteById(Long id);

    void updatePhysicServer(PhysicServerEntity physicServerEntity);

    PhysicServerEntity findEntityById(Long id);

    PageInfo<PhysicServerEntity> getPhysicServerByParm(String param, int pageNum, int pageSize);

    boolean exportToExcel(PhysicServerCond physicServerCond);
}

