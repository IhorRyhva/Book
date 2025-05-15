package com.mtFirstProject.Booking.payment;
import com.mtFirstProject.Booking.book.data.PaymentType;
import lombok.Builder;

@Builder
public record PaymentResponse(
        String userName,
        PaymentType paymentType,
        Integer cardNumber,
        Integer value)
{
}
