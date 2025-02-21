package com.example.gateway;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewaySwaggerConfig {

    @Bean
    public GroupedOpenApi bookingApi() {
        return GroupedOpenApi.builder()
                .group("booking-service")
                .pathsToMatch("/books/**")
                .build();
    }

    @Bean
    public GroupedOpenApi hotelApi() {
        return GroupedOpenApi.builder()
                .group("hotel-microservice")
                .pathsToMatch("/hotels/**")
                .build();
    }

    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder()
                .group("payment-service")
                .pathsToMatch("/payment/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user-service")
                .pathsToMatch("/User/**")
                .build();
    }
}

