package com.onebooming.frastructure.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 15:28
 */
@SpringBootTest
public class ContentDaoTest {
    @Autowired
    ContentDao contentDao;
    @Test
    public void contentDaoTest(){
        System.out.println(contentDao.getArticleCount());
    }
}
