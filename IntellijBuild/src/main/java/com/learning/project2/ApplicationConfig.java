package com.learning.project2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@ComponentScan
@EnableAutoConfiguration
@EnableSpringDataWebSupport
@Configuration
public class ApplicationConfig {

}

