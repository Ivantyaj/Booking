package com.booking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@ComponentScan("com.booking")
public class WebConfig {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${mail.debug}")
    private String debug;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebCorsConfig();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setHost(host);
        mailSender.setPort(port);
        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", protocol);
        props.setProperty("mail.debug", debug);

        return mailSender;
    }

}
