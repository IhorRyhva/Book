package com.mtFirstProject.Booking.payment;

public record PaymentRequest(
        Integer value,
        String email,
        Integer cardNumber,
        PaymentType paymentType
) {
}
