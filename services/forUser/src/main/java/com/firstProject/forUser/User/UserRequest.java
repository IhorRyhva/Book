package com.firstProject.forUser.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        int id,
        @NotNull(message = "firstName is required")
        String firstName,
        @NotNull(message = "lastName is required")
        String lastName,
        @NotNull(message = "email is required")
        @Email(message = "write your email")
        String email
) {
}
