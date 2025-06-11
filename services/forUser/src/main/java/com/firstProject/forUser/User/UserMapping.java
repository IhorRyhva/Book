package com.firstProject.forUser.User;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserMapping {
    public UserResponse toResponse(Optional<User> optionalUser) {
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserResponse
                    .builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstname())
                    .lastName(user.getLastname())
                    .id(user.getId())
                    .build();
        }else{
            return UserResponse.builder()
                    .email("unknown")
                    .firstName("unknown")
                    .lastName("unknown")
                    .id(0)
                    .build();
        }
    }

    public User fromRequest(UserRequest request) {
        return User
                .builder()
                .lastname(request.lastName())
                .firstname(request.firstName())
                .email(request.email())
                .build();
    }

    public UserResponse toResponseAll(User user) {
        return UserResponse
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .id(user.getId())
                .build();
    }

    public User fromTokenClaims(Map<String, Object> claims) {
        User user = new User();

        if(claims.containsKey("given_name")){
            user.setFirstname(claims.get("given_name").toString());
        }
        if (claims.containsKey("family_name")){
            user.setLastname(claims.get("family_name").toString());
        }
        if(claims.containsKey("email")){
            user.setEmail(claims.get("email").toString());
        }

        return user;
    }
}
