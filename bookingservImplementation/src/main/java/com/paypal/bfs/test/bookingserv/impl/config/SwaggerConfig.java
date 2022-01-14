package com.paypal.bfs.test.bookingserv.impl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paypal.bfs.test.bookingserv.impl"))
                .paths(regex("/v1/bfs/.*"))
                .build();
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Booking API",
                "REST API for Booking",
                "1.0",
                "",
                new Contact("Srishti Srivastava", "https://github.com/srishti25s/bookingserv", "srishti.srivastava25@icloud.com"),
                "",
                "");
        return apiInfo;
    }
}