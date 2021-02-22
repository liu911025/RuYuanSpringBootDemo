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
    public void saveUser(User user) {
        userDAO.saveUser(user);
        //int i = 1 / 0;
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void removeUser(Long userId) {
        userDAO.removeUser(userId);
    }
}
