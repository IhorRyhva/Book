package com.mtFirstProject.Booking.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${spring.config.user-url}"
)
public interface UserClient {
    @GetMapping("findByName/{email}")
    Optional<UserResponse> findByName(@PathVariable("email") String email);
}
