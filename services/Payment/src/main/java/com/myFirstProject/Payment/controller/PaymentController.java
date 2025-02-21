package com.myFirstProject.Payment.controller;

import com.myFirstProject.Payment.forPayment.EmailService;
import com.myFirstProject.Payment.forPayment.PaymentService;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import com.myFirstProject.Payment.paymentData.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    @PostMapping("create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(service.createPayment(paymentRequest));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        service.deletePayment(id);
        return ResponseEntity.accepted().build();
    }
}
