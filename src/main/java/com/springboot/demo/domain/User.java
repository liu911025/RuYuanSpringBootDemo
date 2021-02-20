package com.springboot.demo.domain;


import com.springboot.demo.validator.Age;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class User {

    public interface Update{}
    public interface Add{}

    @NotNull(groups = {Update.class})
    @Null(groups = {Add.class})
    private Long id;

    @Size(min=2, max=20, groups = {Update.class, Add.class})
    private String name;

    //@Range(min=1, max=120)
    @Age(max = 125, groups = {Update.class, Add.class})
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
