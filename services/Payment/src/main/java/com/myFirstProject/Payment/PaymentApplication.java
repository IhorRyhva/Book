package com.myFirstProject.Payment;

import com.myFirstProject.Payment.forPayment.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PaymentApplication {
//	@Autowired
//	EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void test(){
//		emailService.send("gorishok465@gmail.com", "test", "test");
//		System.out.println("ok");
//	}
}
