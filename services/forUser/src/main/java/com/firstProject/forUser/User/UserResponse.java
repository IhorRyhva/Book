package com.firstProject.forUser.User;

import lombok.Builder;

@Builder
public record UserResponse(
        int id,
        String firstName,
        String lastName,
        String email) {
}
