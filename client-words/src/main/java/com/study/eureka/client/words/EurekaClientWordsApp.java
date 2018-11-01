package com.study.eureka.client.words;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientWordsApp {

    public static void main(String[] args){
        SpringApplication.run(EurekaClientWordsApp.class, args);
    }
}
