package com.onebooming.frastructure.service.phsicServer.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.ErrorConstant;
import com.onebooming.frastructure.dao.PhysicServerDao;
import com.onebooming.frastructure.dto.cond.PhysicServerCond;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.PhysicServerEntity;
import com.onebooming.frastructure.service.phsicServer.PhysicServerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/13 17:00
 * @Redis数据中 统一定义PhysicServerEntity对象的key为String key = "PhysicServer_" + id;形式
 */
@Service
public class PhysicServerServiceImpl implements PhysicServerService {
    @Autowired
    PhysicServerDao physicServerDao;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    @Override
    public PageInfo<PhysicServerEntity> getPhysicServerByCond(PhysicServerCond physicServerCond, int pageNum, int pageSize) {
        if (null == physicServerCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        List<PhysicServerEntity> physicServerEntityList = physicServerDao.getPhysicServersByCond(physicServerCond);
        PageInfo<PhysicServerEntity> pageInfo = new PageInfo<>(physicServerEntityList);
        return pageInfo;
    }

    /**
     * 加入缓存机制
     *
     * @param physicServerCond
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<PhysicServerEntity> getPhysicServerByCond2(PhysicServerCond physicServerCond, int pageNum, int pageSize) {
        if (null == physicServerCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        //分页设置
        PageHelper.startPage(pageNum, pageSize);
        /**
         * 因为考虑到分页的逻辑，每次只取当前页的的pageSize个对象。
         * 现在业务逻辑出现的问题的是：当删除一个对象，则整体的分页对象都应该改变，但是如果加入缓存，则需要删除所有
         */
        String keyPage = "allPhysicservers_" + pageNum;

        //运行计时
        Long start = System.currentTimeMillis();
        long end;
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        PageInfo<PhysicServerEntity> pageInfo;
        //查询缓存
        List<PhysicServerEntity> physicServerEntityList = (List<PhysicServerEntity>) redisTemplate.opsForValue().get(keyPage);
        if (null == physicServerEntityList) {
            //缓存为空，则从mysql数据库中查询
            physicServerEntityList = physicServerDao.getPhysicServersByCond(physicServerCond);
            pageInfo = new PageInfo<>(physicServerEntityList);
            end = System.currentTimeMillis();
            System.out.println("从MySql数据库中查询......总计耗时：" + (end - start));
            //从数据库查询完毕后，将对象存入缓存
            redisTemplate.opsForValue().set(keyPage, physicServerEntityList);
            //将数据库中的每个PhysicServerEntity以key-value的形式逐一放入缓存中
            for (PhysicServerEntity p : physicServerEntityList) {
                String key = "PhysicServer_" + p.getId();
                if (!redisTemplate.hasKey(key)) {
                    //如果不存在key，则加入缓存
                    redisTemplate.opsForValue().set(key, p, 5, TimeUnit.HOURS);
                }
            }
        } else {
            end = System.currentTimeMillis();
            System.out.println("从缓存中（Redis）查询......总计耗时：" + (end - start));
            pageInfo = new PageInfo<>(physicServerEntityList);
        }


        return pageInfo;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //先删除缓存，再删除数据库中的对象
        String key = "PhysicServer_" + id;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }

        physicServerDao.deletePhysicServerById(id);

    }

    @Override
    public void updatePhysicServer(PhysicServerEntity physicServerEntity) {
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        String key = "PhysicServer_" + physicServerEntity.getId();
        //如果缓存中存在，则先删除，然后将新的更新后的对象加入缓存中
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, physicServerEntity, 5, TimeUnit.HOURS);
        //更新数据库中的操作
        physicServerDao.updatePhysicServer(physicServerEntity);
    }

    @Override
    public PhysicServerEntity findEntityById(Long id) {
        if (id == null) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //定义键值key
        String key = "PhysicServer_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        PhysicServerEntity physicServerEntity = new PhysicServerEntity();
        if (hasKey) {
            //如果缓存中存在，则从缓存中查询
            physicServerEntity = (PhysicServerEntity) redisTemplate.opsForValue().get(key);
            System.out.println("从缓存中（Redis）查询......");
        } else {
            //从数据库中查询
            physicServerEntity = physicServerDao.getPhysicServerById(id);
            //将查询结果添加到缓存中
            redisTemplate.opsForValue().set(key, physicServerEntity, 5, TimeUnit.HOURS);
            System.out.println("从缓存中（Redis）查询......");
        }

        return physicServerEntity;
    }

    @Override
    public PageInfo<PhysicServerEntity> getPhysicServerByParm(String param, int pageNum, int pageSize) {
        if (null == param)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum, pageSize);
        //模糊搜索
        List<PhysicServerEntity> physicServerEntityList = physicServerDao.searchPhysicServer(param);
        //实现分页
        PageInfo<PhysicServerEntity> pageInfo = new PageInfo<>(physicServerEntityList);
        return pageInfo;
    }

    @Override
    public boolean exportToExcel(PhysicServerCond physicServerCond) {
        List<PhysicServerEntity> physicServerEntityList = physicServerDao.getPhysicServersByCond(physicServerCond);
        for (PhysicServerEntity p : physicServerEntityList) {
            System.out.println(p);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("服务器信息", "服务器"),
                PhysicServerEntity.class, physicServerEntityList);
        String fileName = "PhysicServers";
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
}