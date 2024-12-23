package com.myFirstProject.Payment.forPayment;

import com.myFirstProject.Payment.paymentData.PaymentRepository;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapping mapping;
    private final EmailService emailService;
    public Long createPayment(PaymentRequest paymentRequest) {
        long result = repository.save(mapping.toPayment(paymentRequest)).getId();
        emailService.send(paymentRequest.email(), "Successful payment", "Wish you a good rest");
        return result;
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
