package com.mtFirstProject.Booking.payment;

import com.mtFirstProject.Booking.book.data.PaymentType;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentRequest create(Integer value, String email, Integer cardNumber, PaymentType paymentType, String message) {
        return PaymentRequest.builder()
                .value(value)
                .cardNumber(cardNumber)
                .email(email)
                .paymentType(paymentType)
                .message(message)
                .build();
    }
}
