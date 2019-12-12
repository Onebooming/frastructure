package com.onebooming.frastructure.dao;


import com.onebooming.frastructure.model.LogDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 14:04
 */
@Mapper
public interface LogDao {

    /**
     * 添加日志
     * @param logDomain
     * @return
     */
    int addLog(LogDomain logDomain);

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     * @return
     */
    List<LogDomain> getLogs();
}
