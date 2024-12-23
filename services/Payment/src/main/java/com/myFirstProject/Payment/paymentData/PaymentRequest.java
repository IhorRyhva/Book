package com.myFirstProject.Payment.paymentData;
public record PaymentRequest(
        Integer value,
        String email,
        Integer cardNumber,
        PaymentType paymentType
) {
}
