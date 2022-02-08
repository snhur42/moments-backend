package com.instacafe.moments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-default.properties")
public class MomentsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomentsBackendApplication.class, args);
    }

}
