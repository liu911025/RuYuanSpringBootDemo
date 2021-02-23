package com.springboot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.demo.domain.User;
import com.springboot.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
/*
 * 通过这个注解表明，要测试的controller是哪个
 */
@WebMvcTest(controllers = {UserController.class})
public class UserControllerTest {
    /**
     * 注入一个MockMvc，模拟对controller发起http请求
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * 模拟userService组件
     */
    @MockBean
    private UserService userService;

    @Test
    public void listUsers() {
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setId(1L);
        user.setName("测试用户");
        user.setAge(20);

        users.add(user);
        when(userService.listUsers()).thenReturn(users);
        try {
            mockMvc.perform(get("/user/").param("_login", "true")).andExpect(content().json(JSON.toJSONString(users)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(1L);
        user.setName("测试用户");
        user.setAge(20);
        when(userService.getUserById(any())).thenReturn(user);
        try {
            mockMvc.perform(get("/user/{id}", userId).param("_login", "true")).andExpect(content().json(JSON.toJSONString(user)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveUser() {
        Long userId = 1L;
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);
        when(userService.saveUser(user)).thenReturn(userId);
        try {
            mockMvc.perform(
                    post("/user/")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .param("_login", "true")
                            .content(JSON.toJSONString(user))
            ).andExpect(content().string("success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("测试用户");
        user.setAge(20);

        when(userService.updateUser(user)).thenReturn(true);

        try {
            mockMvc.perform(put("/user/{id}", userId).contentType("application/json").content(JSONObject.toJSONString(user)).param("_login", "true"))
                    .andExpect(content().string("success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser() {
        Long userId = 1L;

        when(userService.removeUser(userId)).thenReturn(true);

        try {
            mockMvc.perform(delete("/user/{id}", userId).param("_login", "true"))
                    .andExpect(content().string("success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}