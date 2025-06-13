package com.myFirstProject.Payment.controller;

import com.myFirstProject.Payment.forPayment.PaymentService;
import com.myFirstProject.Payment.paymentData.PaymentRequest;
import com.myFirstProject.Payment.paymentData.PaymentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/")
@Tag(name = "Book")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    @PostMapping("create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        System.out.println(paymentRequest);
        return ResponseEntity.ok(service.createPayment(paymentRequest));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        service.deletePayment(id);
        return ResponseEntity.accepted().build();
    }
}
