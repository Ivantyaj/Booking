package com.booking_maiseyenka_stepovoi.utils_maiseyenka_stepovoi.logging_maiseyenka_stepovoi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan({"com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi"})
public class LoggerConfig {

    @Bean
    public HttpServletLogger loggingFilter() {
        return new HttpServletLogger();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        return restTemplate;
    }


}
