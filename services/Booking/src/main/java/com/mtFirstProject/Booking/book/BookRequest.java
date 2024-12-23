package com.mtFirstProject.Booking.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.NonNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
public record BookRequest(
        LocalDate settlement,
        LocalDate eviction,
        @NotNull
        @NotBlank
        @NotEmpty
        String hotel_name,
        @Positive
        Integer roomId,
        @NotNull
        @NotBlank
        @NotEmpty
        String userEmail,
        Long payment_id
){
}
