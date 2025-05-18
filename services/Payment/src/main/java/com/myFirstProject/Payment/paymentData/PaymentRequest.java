package com.myFirstProject.Payment.paymentData;

import lombok.Builder;

@Builder
public record PaymentRequest(
        Integer value,
        String email,
        Integer cardNumber,
        PaymentType paymentType,
        String message
) {
}
