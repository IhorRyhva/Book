package com.mtFirstProject.Booking.book.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;


import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
public record BookResponse(
        LocalDate settlement,
        LocalDate eviction,
        String hotel_name,
        Integer roomId,
        String userEmail
) {
}
