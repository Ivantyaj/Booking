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
    @Value("${spring.mail.tls}")
    private String tls;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${mail.smtp.socketFactoryPort}")
    private String socketFactory;
    @Value("${mail.smtp.socketFactory.port}")
    private String socketFactoryPort;
    @Value("${mail.smtp.socketFactory.fallback}")
    private String fallback;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String ssl;
    @Value("${mail.smtp.socketFactory.class}")
    private String factoryClass;
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
        props.setProperty("mail.protocol",protocol);
        props.setProperty("mail.tls",tls);
        props.setProperty("mail.properties.mail.smtp.auth",auth);
        props.setProperty("mail.smtp.socketFactoryPort",socketFactoryPort);
        props.setProperty("mail.smtp.socketFactory",socketFactory);
        props.setProperty("mail.smtp.socketFactory.fallback",fallback);
        props.setProperty("mail.properties.mail.smtp.starttls.enable",starttls);
        props.setProperty("mail.properties.mail.smtp.ssl.trust",ssl);
        props.setProperty("mail.smtp.socketFactory.class",factoryClass);
        props.setProperty("mail.debug",debug);

        return mailSender;
    }

}
