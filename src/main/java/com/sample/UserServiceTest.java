package com.sample;

import com.sample.config.DaoFactory;
import com.sample.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserService userService = applicationContext.getBean(UserService.class);

    }
}
