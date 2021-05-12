package com.revaturemax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Driver {

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
    }
}
