package com.mtFirstProject.Booking.book.data;

import java.time.LocalDate;

public record DateOfBook(
        LocalDate settlement,
        LocalDate eviction
) {
}
