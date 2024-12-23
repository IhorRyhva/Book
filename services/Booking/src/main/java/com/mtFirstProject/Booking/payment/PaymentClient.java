package com.mtFirstProject.Booking.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${spring.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping("create")
    ResponseEntity<Long> createPayment(@RequestBody PaymentRequest paymentRequest);
    @DeleteMapping("delete/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id") Long id);
}
