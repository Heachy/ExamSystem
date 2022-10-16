package com.lczyfz.edp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 86189
 */ //@EnableWebMvc
@ComponentScan("com.lczyfz.*")
@EnableSwagger2
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        SecurityAutoConfiguration.class})
//@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
public class ExamsystemMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsystemMainApplication.class, args);
    }

}
