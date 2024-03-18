package com.example.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
@EnableCaching // для аннотаций, связанных с кэшом
public class TasklistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasklistApplication.class, args);
    }

}
