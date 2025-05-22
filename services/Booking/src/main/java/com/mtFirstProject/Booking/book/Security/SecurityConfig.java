package com.mtFirstProject.Booking.book.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/ws/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/books/search").permitAll()
                        .requestMatchers(
                                "/books/all",
                                "/books/all/*",
                                "/books/delete/*")
                        .hasAuthority("client-admin")
                        .anyRequest().permitAll())
                .oauth2ResourceServer(outh2 -> outh2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtConvertor())
                ));
        return http.build();
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:9090/realms/book-hotel/protocol/openid-connect/certs").build();
    }
    @Bean
    public JwtAuthenticationConverter jwtConvertor() {
        JwtConvert jwtConverter = new JwtConvert();

        JwtAuthenticationConverter jwtAuthentication = new JwtAuthenticationConverter();
        jwtAuthentication.setJwtGrantedAuthoritiesConverter(jwtConverter);
        return jwtAuthentication;
    }
}
