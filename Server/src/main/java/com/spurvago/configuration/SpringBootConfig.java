package com.spurvago.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Configuration
@ComponentScan({"com.spurvago.server", "com.spurvago.security", "com.spurvago.configuration"})
public class SpringBootConfig {
}
