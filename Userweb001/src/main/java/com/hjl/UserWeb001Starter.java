package com.hjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class UserWeb001Starter {
    public static void main(String[] args) {
        SpringApplication.run(UserWeb001Starter.class,args);
    }
    @Bean
    public RestTemplate getRestTemplate(){
        System.out.println("nihao");
        System.out.println("郝");
        return new RestTemplate();
    }
}
