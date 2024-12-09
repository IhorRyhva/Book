package com.mtFirstProject.Booking.user;

import lombok.Builder;

@Builder
public record UserResponse(
        int id,
        String firstName,
        String lastName,
        String email) {
}
