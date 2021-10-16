package com.learning.project2;

import com.learning.project2.models.user.User;
import com.learning.project2.models.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;

@SpringBootApplication
public class Project2Application {


    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }


}
