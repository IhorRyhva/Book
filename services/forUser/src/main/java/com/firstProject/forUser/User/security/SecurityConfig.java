package com.firstProject.forUser.User.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http
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
                .requestMatchers("/User/all")
                        .hasAuthority("client-admin")
                .anyRequest()
                    .permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(authConvertor())
                ));

        http.formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:9090/realms/book-hotel/protocol/openid-connect/certs").build();
    }

    @Bean
    public JwtAuthenticationConverter authConvertor(){
        JwtConverter jwtConverter = new JwtConverter();

        JwtAuthenticationConverter jwtAuthentication = new JwtAuthenticationConverter();
        jwtAuthentication.setJwtGrantedAuthoritiesConverter(jwtConverter);
        return jwtAuthentication;
    }
}
