package com.sample.config;

import com.sample.dao.UserDaoJdbc;
import com.sample.service.DummyMailSender;
import com.sample.service.UserService;
import com.sample.service.UserServiceImpl;
import com.sample.service.UserServiceTx;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DaoFactory {

    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl()
            .userDao(userDao())
            .mailSender(mailSender());
        return new UserServiceTx()
            .userService(userService);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public UserDaoJdbc userDao() {
        return new UserDaoJdbc().setJdbcTemplate(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mariadb://localhost:3307/toby");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}