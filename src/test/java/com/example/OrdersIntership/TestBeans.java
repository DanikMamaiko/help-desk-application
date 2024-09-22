package com.example.OrdersIntership;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
public class TestBeans {

    @Bean
    @ServiceConnection
    public MySQLContainer <?> mySQLContainer() {
        return new MySQLContainer<>("mysql:8.0.33");
    }

}
