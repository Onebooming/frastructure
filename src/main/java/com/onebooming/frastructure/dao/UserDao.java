package com.onebooming.frastructure.dao;

import com.onebooming.frastructure.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 14:04
 */
@Mapper
public interface UserDao {
    /**
     * 更改用户信息
     * @param user
     * @return
     */
    int updateUserInfo(UserDomain user);

    /**
     * 根据主键编号获取用户信息
     * @param uid
     * @return
     */
    UserDomain getUserInfoById(@Param("uid") Integer uid);

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    UserDomain getUserInfoByCond(@Param("username") String username, @Param("password") String password);
}
