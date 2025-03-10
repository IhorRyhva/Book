package com.mtFirstProject.Booking.payment;

import com.mtFirstProject.Booking.book.PaymentType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentRequest(
        Integer value,
        String email,
        Integer cardNumber,
        PaymentType paymentType
) {
}
