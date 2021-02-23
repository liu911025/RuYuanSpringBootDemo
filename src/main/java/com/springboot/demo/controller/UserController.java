package com.springboot.demo.controller;

import com.springboot.demo.domain.User;
import com.springboot.demo.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value="/user")
@RestController
public class UserController {

    private Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;
 
    // GET请求代表着是查询数据
    @GetMapping(value="/")
    public List<User> listUsers() {
        log.debug("查询用户");
        return userService.listUsers();
    } 
    
    // GET请求+{id}代表的是查询某个用户
    @GetMapping(value="/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    } 
    
    // POST请求代表着是新增数据
    @PostMapping(value="/")
    public String saveUser(@Validated ({User.Add.class}) @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError error = (FieldError)objectError;
                System.out.println(error.getObjectName() + ","+ error.getField() + "," + error.getDefaultMessage());
                sb.append(error.getObjectName() + ","+ error.getField() + "," + error.getDefaultMessage() + "-");
            }
            return sb.toString();
        }
        userService.saveUser(user);
        return "success";
    } 
    
    // PUT请求代表的是更新
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String updateUser(@Validated({User.Update.class}) @RequestBody User user) {
        userService.updateUser(user);
        return "success"; 
    } 
    
    // DELETE请求代表的是删除
    @DeleteMapping(value="/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "success"; 
    }

}
