package com.springboot.demo.dao;

import com.springboot.demo.domain.User;

import java.util.List;

public interface UserDAO {

    List<User> listUsers();

    User getUserById(Long userId);

    void saveUser(User user);

    void updateUser(User user);

    void removeUser(Long userId);
}
