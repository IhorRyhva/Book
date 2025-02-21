package com.mtFirstProject.Booking.payment;

import com.mtFirstProject.Booking.book.PaymentType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentService {
    public PaymentRequest create(Integer valuePerNight, int numberOfNight, String email, Integer cardNumber, PaymentType paymentType) {
        return PaymentRequest.builder()
                .value(valuePerNight*numberOfNight)
                .cardNumber(cardNumber)
                .email(email)
                .paymentType(paymentType)
                .build();
    }
}
