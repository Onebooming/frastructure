package com.onebooming.frastructure.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentDaoTest {
    @Autowired
    ContentDao contentDao;
    @Test
    public void contentDaoTest(){
        System.out.println(contentDao.getArticleCount());
    }
}
