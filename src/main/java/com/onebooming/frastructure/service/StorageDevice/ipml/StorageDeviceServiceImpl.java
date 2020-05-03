package com.onebooming.frastructure.service.StorageDevice.ipml;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.constant.ErrorConstant;
import com.onebooming.frastructure.dao.StorageDeviceDao;
import com.onebooming.frastructure.exception.BusinessException;
import com.onebooming.frastructure.model.StorageDevice;
import com.onebooming.frastructure.service.StorageDevice.StorageDeviceService;
import com.onebooming.frastructure.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-23 21:27
 * @ApiNote Redis分页缓存的一些思路：
 * Redis缓存分页
 * 1.数据以ID为key缓存到Redis里；
 * 2.把数据ID和排序打分存到Redis的skip list，即zset里；
 * 3.当查找数据时，先从Redis里的skip list取出对应的分页数据，得到ID列表。
 * 4.用multi get从redis上一次性把ID列表里的所有数据都取出来。
 * 如果有缺少某些ID的数据，再从数据库里查找，再一块返回给用户，并把查出来的数据按ID缓存到Redis里。
 */
@Service
public class StorageDeviceServiceImpl implements StorageDeviceService {
    @Autowired
    StorageDeviceDao storageDeviceDao;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public PageInfo<StorageDevice> getStorageDevice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        /**
         * 因为考虑到分页的逻辑，每次只取当前页的的pageSize个对象。
         * 现在业务逻辑出现的问题的是：当删除一个对象，则整体的分页对象都应该改变，但是如果加入缓存，则需要删除所有
         */
        String keyPage = "pageStorageDevice" + pageNum;
        //字符串序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        PageInfo<StorageDevice> pageInfo ;
        //查询缓存
        List<StorageDevice> storageDeviceList = (List<StorageDevice>) redisTemplate.opsForValue().get(keyPage);
        if (null == storageDeviceList) {
            //缓存为空，则从数据库查询
            storageDeviceList = storageDeviceDao.getAllStorageDevices();
            /**
             * 查询后的结果集根据StorageDevice的Id排序
             */
            storageDeviceList.sort(new Comparator<StorageDevice>() {
                @Override
                public int compare(StorageDevice o1, StorageDevice o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
            //将数据库中的每个StorageDevice对象以key-value的形式逐一放入缓存中；
            for (StorageDevice sd : storageDeviceList) {
                String key = String.valueOf(sd.getId());
                if (!redisTemplate.hasKey(key)) {
                    //如果不存在key,则加入缓存
                    redisTemplate.opsForValue().set(key, sd, 5, TimeUnit.HOURS);
                }
            }
            pageInfo = new PageInfo<>(storageDeviceList);
            //数据库查询完毕后将对象存入缓存
            redisTemplate.opsForValue().set(keyPage, storageDeviceList);
        } else {
            pageInfo = new PageInfo<>(storageDeviceList);
        }

        return pageInfo;
    }

    @Override
    @Transactional
    public void deletById(Long id) {
        if (null == id) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        //先删除数据库中的数据，再删除缓存
        StorageDevice sd = storageDeviceDao.getStorageDeviceById(id);
        storageDeviceDao.deleteStorageDevice(id);
        String key = String.valueOf(sd.getId());
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public void updateStorageDevice(StorageDevice storageDevice) {
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        String key = String.valueOf(storageDevice.getId());
        //如果缓存中存在，则先删除，然后将新的更新后对象加入缓存中
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, storageDevice, 5, TimeUnit.HOURS);
        //更新数据操作
        storageDeviceDao.updateStorageDevice(storageDevice);
    }

    @Override
    public StorageDevice findStorageDeviceById(Long id) {
        if (null == id) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        //字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //定义键值key
        String key = String.valueOf(id);
        boolean hasKey = redisTemplate.hasKey(key);
        StorageDevice storageDevice;
        if (hasKey) {
            //如果缓存中存在，则在缓存中查询
          storageDevice =   (StorageDevice) redisTemplate.opsForValue().get(key);
        }else {
            //从数据库中查询
            storageDevice = storageDeviceDao.getStorageDeviceById(id);
            //将查询结果添加到缓存中
            redisTemplate.opsForValue().set(key,storageDevice,5,TimeUnit.HOURS);
        }

        return storageDevice;

    }


    @Override
    public PageInfo<StorageDevice> getStorageDeviceByParamPage(String param, int pageNum, int pageSize) {
        if(null == param){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        PageHelper.startPage(pageNum, pageSize);
        //模糊搜索
        List<StorageDevice> storageDeviceList = storageDeviceDao.searchStorageDevice(param);
       //分页算法
       PageInfo<StorageDevice> pageInfo = new PageInfo<>(storageDeviceList);
       return pageInfo;
    }

    @Override
    public boolean exportToExcel(StorageDevice storageDevice) {
        List<StorageDevice> storageDeviceList = storageDeviceDao.getAllStorageDevices();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("存储设备信息", "存储设备"),
                StorageDevice.class, storageDeviceList);
        String fileName = "StorageDevice_";

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

        StorageDevice storageDevice = JSON.parseObject(JSON.toJSONString(params), StorageDevice.class);
        System.out.println(storageDevice.toString());
        int i = storageDeviceDao.updateStorageDevice(storageDevice);
        return i > 0 ? true : false;
    }
}
