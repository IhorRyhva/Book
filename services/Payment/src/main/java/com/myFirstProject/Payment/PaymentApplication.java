package com.myFirstProject.Payment;

import com.myFirstProject.Payment.forPayment.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}
