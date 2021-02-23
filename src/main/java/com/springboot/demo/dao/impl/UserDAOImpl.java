package com.springboot.demo.dao.impl;

import com.springboot.demo.dao.UserDAO;
import com.springboot.demo.domain.User;
import com.springboot.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserMapper userMapper;
        
    public List<User> listUsers() {
        return userMapper.listUsers();
      }
        
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
      }

      public Long saveUser(User user) {
        userMapper.saveUser(user);
        return user.getId();
      }
        
      public Boolean updateUser(User user) {
        userMapper.updateUser(user);
        return true;
      }
        
      public Boolean removeUser(Long userId) {
        userMapper.removeUser(userId);
        return true;
      }
}
