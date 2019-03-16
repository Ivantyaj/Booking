package com.booking.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.booking.controller")
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.booking.controller"))
//                .apis(RequestHandlerSelectors.any())
                .paths(regex("/.*"))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(metaData());
    }

    // Describe your apis
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Booking")
                .description("API проекта \"Booking\"")
                .version("v1.0")
                .license("672302")
                .build();
    }

}
