package com.spurvago.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Configuration
@EntityScan("com.spurvago.database")
@CrossOrigin
public class ServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
