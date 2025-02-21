package com.myFirstProject.Payment.forPayment;

import com.myFirstProject.Payment.paymentData.Payment;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import com.myFirstProject.Payment.paymentData.PaymentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentMapping {

    public Payment toPayment(PaymentRequest paymentRequest, LocalDate timeOfPay) {
        return Payment.builder()
                .paymentType(paymentRequest.paymentType())
                .value(paymentRequest.value())
                .userName(paymentRequest.email())
                .cardNumber(paymentRequest.cardNumber())
                .timeOfPay(timeOfPay)
                .build();
    }

    public PaymentResponse toResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentType(payment.getPaymentType())
                .cardNumber(payment.getCardNumber())
                .userName(payment.getUserName())
                .value(payment.getValue())
                .build();
    }
}
