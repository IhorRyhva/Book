package com.mtFirstProject.Booking;

import com.mtFirstProject.Booking.book.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class BookingApplication {
//	@Autowired
//	private EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void test(){
//		emailService.sendEmail("gorishok465@gmail.com", "test", "test");
//		System.out.println("ok");
//	}
}
