package com.myFirstProject.Payment.forPayment;

import com.myFirstProject.Payment.paymentData.Payment;
import com.myFirstProject.Payment.paymentData.PaymentRepository;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import com.myFirstProject.Payment.paymentData.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapping mapping;
    private final EmailService emailService;
    private final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        LocalDate pay = LocalDate.now();
        Payment payment = repository.save(mapping.toPayment(paymentRequest, pay));
        String time = CUSTOM_FORMATTER.format(pay);
        String s = String.format("%s successfully paid %n in %s", paymentRequest.email(), paymentRequest.value(), time);
        emailService.send(paymentRequest.email(), "Payment", s);
        return mapping.toResponse(payment);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
