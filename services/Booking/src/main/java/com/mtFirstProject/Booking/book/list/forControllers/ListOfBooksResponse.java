package com.mtFirstProject.Booking.book.list.forControllers;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ListOfBooksResponse(
         LocalDate settlement,
         LocalDate eviction,
         String hotel_name,
         Integer roomNumber
) {
}
