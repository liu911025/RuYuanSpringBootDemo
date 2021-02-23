package com.springboot.demo.mapper;

import com.springboot.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> listUsers();
        
    @Select("SELECT * FROM user WHERE id = #{userId}")
    @Results({
             @Result(property = "id", column = "id", id = true),
             @Result(property = "name", column = "name"),
             @Result(property = "age", column = "age")
            })
    User getUserById(@Param("userId") Long userId);
        
    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveUser(User user);
        
    @Update("UPDATE user SET name=#{name}, age=#{age} WHERE id=#{id}")
    void updateUser(User user);
        
    @Delete("DELETE FROM user WHERE id=#{userId}")
    void removeUser(@Param("userId") Long userId);
}
