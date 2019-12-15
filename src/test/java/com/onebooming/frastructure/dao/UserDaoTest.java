package com.onebooming.frastructure.dao;

import com.onebooming.frastructure.model.UserDomain;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 15:00
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void UserDaoTest() {
        UserDomain user = new UserDomain();
        //user = userDao.getUserInfoById(1);
        user = userDao.getUserInfoByCond("admin","123456");
        System.out.println(user.getScreenName());
    }
}
