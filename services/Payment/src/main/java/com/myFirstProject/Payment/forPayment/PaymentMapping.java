package com.myFirstProject.Payment.forPayment;

import com.myFirstProject.Payment.paymentData.Payment;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapping {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .paymentType(paymentRequest.paymentType())
                .value(paymentRequest.value())
                .userName(paymentRequest.email())
                .cardNumber(paymentRequest.cardNumber())
                .build();
    }
}
