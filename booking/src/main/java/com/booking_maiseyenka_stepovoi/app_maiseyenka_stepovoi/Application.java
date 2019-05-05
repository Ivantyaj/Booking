package com.booking_maiseyenka_stepovoi.app_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.config_maiseyenka_stepovoi.DatabaseConfig;
import com.booking_maiseyenka_stepovoi.utils_maiseyenka_stepovoi.logging_maiseyenka_stepovoi.LoggerConfig;
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
