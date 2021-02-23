package com.springboot.demo.service;

import com.springboot.demo.domain.User;

import java.util.List;

public interface UserService {

    List<User> listUsers();

    User getUserById(Long userId);

    Long saveUser(User user);

    Boolean updateUser(User user);

    Boolean removeUser(Long userId);

}
