package com.springboot.demo.dao;

import com.springboot.demo.domain.User;

import java.util.List;

public interface UserDAO {

    List<User> listUsers();

    User getUserById(Long userId);

    Long saveUser(User user);

    Boolean updateUser(User user);

    Boolean removeUser(Long userId);
}
