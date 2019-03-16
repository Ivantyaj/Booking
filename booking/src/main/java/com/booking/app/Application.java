package com.booking.app;

import com.booking.config.DatabaseConfig;
import com.booking.utils.logging.LoggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DatabaseConfig.class, LoggerConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}