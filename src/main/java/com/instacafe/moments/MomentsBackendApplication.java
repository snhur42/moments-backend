package com.instacafe.moments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@PropertySource("classpath:application-default.properties")
public class MomentsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomentsBackendApplication.class, args);
    }

//    @Configuration
//    @EnableWebMvc
//    public static class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//        }
//    }


}
