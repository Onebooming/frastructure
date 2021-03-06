package com.onebooming.frastructure.dao;

import com.onebooming.frastructure.model.CommentDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 15:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentDaoTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void commentTest() {
        CommentDomain commentDomain = new CommentDomain();
        commentDomain.setCid(3);
        commentDomain.setContent("hah");
        int i = commentDao.addComment(commentDomain);
        System.out.println(i);
    }
}
