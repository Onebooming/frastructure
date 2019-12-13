package com.onebooming.frastructure.service.phsicServer.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.ErrorConstant;
import com.onebooming.frastructure.dao.PhysicServerDao;
import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.ContentDomain;
import com.onebooming.frastructure.model.PhysicServerEntity;
import com.onebooming.frastructure.service.phsicServer.PhysicServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/13 17:00
 */
@Service
public class PhysicServerServiceImpl implements PhysicServerService {
    @Autowired
    PhysicServerDao physicServerDao;
    @Override
    public PageInfo<PhysicServerEntity> getPhysicServerByCond(PhysicServerCond physicServerCond, int pageNum, int pageSize) {
        if (null == physicServerCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        List<PhysicServerEntity> physicServerEntityList = physicServerDao.getPhysicServersByCond(physicServerCond);
        PageInfo<PhysicServerEntity> pageInfo = new PageInfo<>(physicServerEntityList);
        return pageInfo;
    }
}
