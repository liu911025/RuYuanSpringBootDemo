package com.springboot.demo.dao.impl;


import com.springboot.demo.dao.UserDAO;
import com.springboot.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 单元个测试的时候如果不想造成垃圾数据，可以开启事物功能，记在方法或者类头部添加@Transactional注解即可,
 * 测试完数据就会回滚了，不会造成垃圾数据。如果你想关闭回滚，只要加上@Rollback(false)注解即可。
 * @Rollback表示事务执行完回滚，支持传入一个参数value，默认true即回滚，false不回滚。
 * 如果你使用的数据库是Mysql，有时候会发现加了注解@Transactional 也不会回滚，那么你就要查看一下你的默认引擎是不是InnoDB，如果不是就要改成InnoDB
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Rollback(true)
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void listUsers() {
        // 准备好mock userMapper的返回数据
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);
        userDAO.saveUser(user);

        List<User> users = new ArrayList<User>();
        users.add(user);

        // 测试UserSerivce的listUsers()方法
        List<User> resultUsers = userDAO.listUsers();

        // 对测试结果进行断言
        assertEquals(users.size(), resultUsers.size());
    }

    @Test
    public void getUserById() {
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);
        userDAO.saveUser(user);

        Long userId = user.getId();

        User resultUser = userDAO.getUserById(userId);

        assertEquals(user.toString(), resultUser.toString());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);

        Long resultUserId = userDAO.saveUser(user);

        assertThat(resultUserId, is(greaterThan(0L)));    }

    @Test
    public void updateUser() {
        Integer oldAge = 20;
        Integer newAge = 21;

        User user = new User();
        user.setName("测试用户");
        user.setAge(oldAge);
        userDAO.saveUser(user);

        user.setAge(newAge);
        Boolean updateResult = userDAO.updateUser(user);

        assertTrue(updateResult);

        User updatedUser = userDAO.getUserById(user.getId());

        assertEquals(newAge, updatedUser.getAge());
    }

    @Test
    public void removeUser() {
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);
        userDAO.saveUser(user);

        Boolean removeResult = userDAO.removeUser(user.getId());

        assertTrue(removeResult);
    }
}