package com.mtFirstProject.Booking.payment;
import com.mtFirstProject.Booking.book.PaymentType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public record PaymentResponse(
        String userName,
        PaymentType paymentType,
        Integer cardNumber,
        Integer value)
{
}
