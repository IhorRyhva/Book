package com.firstProject.forUser.User;

import org.springframework.stereotype.Service;

@Service
public class UserMapping {
    public UserResponse toResponse(User user) {
        return UserResponse
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .id(user.getId())
                .build();
    }

    public User fromRequest(UserRequest request) {
        return User
                .builder()
                .lastname(request.lastName())
                .firstname(request.firstName())
                .email(request.email())
                .build();
    }
}
