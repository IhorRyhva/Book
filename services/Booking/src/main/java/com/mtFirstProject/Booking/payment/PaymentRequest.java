package com.mtFirstProject.Booking.payment;

import com.mtFirstProject.Booking.book.data.PaymentType;
import lombok.Builder;

@Builder
public record PaymentRequest(
        Integer value,
        String email,
        Integer cardNumber,
        PaymentType paymentType
) {
}
