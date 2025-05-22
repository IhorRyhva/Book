package com.mtFirstProject.Booking.book.data;

import com.mtFirstProject.Booking.hotelAndRoom.enums.Rate;
import com.mtFirstProject.Booking.hotelAndRoom.enums.Stars;

import java.time.LocalDate;

public record ForSort(
        LocalDate settlement,
        LocalDate eviction,
        String location,
        Stars stars,
        Rate rate,
        int maxWorthPerNight,
        int minWorthPerNight,
        int numberOfBed
) {
}
