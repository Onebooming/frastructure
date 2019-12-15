package com.onebooming.frastructure.service.log;

import com.github.pagehelper.PageInfo;
import com.onebooming.frastructure.model.LogDomain;

/**
 * 用户请求日志
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 14:44
 */
public interface LogService {
    /**
     * 添加
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    void addLog(String action, String data, String ip, Integer authorId);

    /**
     * 删除日志
     * @param id
     * @return
     */
    void deleteLogById(Integer id);

    /**
     * 获取日志
     * @return
     */
    PageInfo<LogDomain> getLogs(int pageNum, int pageSize);
}
