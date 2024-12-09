package com.mtFirstProject.Booking.kafka;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record BookConfirmation(
        String userEmail,
        String hotelName,
        int roomNumber,
        LocalDate settlement,
        LocalDate eviction
) {
}
