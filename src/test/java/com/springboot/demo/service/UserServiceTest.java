package com.springboot.demo.service;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.alibaba.fastjson.JSON;
import com.springboot.demo.dao.UserDAO;
import com.springboot.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Test
    public void listUsersTest() {
        Long userId = 1L;
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(userId);
        user.setName("王五");
        user.setAge(12);
        userList.add(user);

        when(userDAO.listUsers()).thenReturn(userList);
        List<User> resultList = userService.listUsers();
        assertEquals(userList.size(), resultList.size());
    }

    @Test
    public void getUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("赵六");
        user.setAge(16);

        when(userDAO.getUserById(userId)).thenReturn(user);
        User userById = userService.getUserById(userId);
        assertEquals(userById.getId(), user.getId());
        System.out.println(JSON.toJSONString(userById));
    }

    @Test
    public void saveUser(){
        Long userId = 1L;
        User user = new User();
        user.setName("赵六");
        user.setAge(16);
        when(userDAO.saveUser(user)).thenReturn(userId);
        Long id = userService.saveUser(user);
        Assert.assertThat(userId, is(id));
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("测试用户");
        user.setAge(20);

        when(userDAO.updateUser(user)).thenReturn(true);

        Boolean updateResult = userService.updateUser(user);

        assertTrue(updateResult);
    }

    /**
     * 测试用例：删除用户
     */
    @Test
    public void testRemoveUser() {
        Long userId = 1L;
        when(userDAO.removeUser(userId)).thenReturn(true);
        Boolean removeResult = userService.removeUser(userId);
        assertTrue(removeResult);
    }
}
