package com.springboot.demo.service.impl;

import com.springboot.demo.dao.UserDAO;
import com.springboot.demo.domain.User;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDAO;

    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    public User getUserById(Long userId) {
        return userDAO.getUserById(userId);
    }

    @Transactional
    public Long saveUser(User user) {
        //int i = 1 / 0;
        return userDAO.saveUser(user);
    }

    public Boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public Boolean removeUser(Long userId) {
        return userDAO.removeUser(userId);
    }
}
