package com.springboot.demo;

import com.springboot.demo.config.DruidDBConfig;
import com.springboot.demo.listener.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(DruidDBConfig.class)
@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringbootDemoApplication.class, args);
        SpringApplication app = new SpringApplication(SpringbootDemoApplication.class);
        app.addListeners(new MyApplicationStartedEventListener());
        app.run(args);
    }

}
