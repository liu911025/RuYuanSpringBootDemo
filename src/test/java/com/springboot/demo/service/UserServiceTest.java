package com.springboot.demo.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import com.springboot.demo.dao.UserDAO;
import com.springboot.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        Assert.assertEquals(userList.size(), resultList.size());
    }
}
