package com.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.booking")
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebCorsConfig();
    }
}
