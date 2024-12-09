package com.mtFirstProject.Booking.book;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Cleaner {
    private final BookRepository bookRepository;
    @Bean
    @Scheduled(cron = "0 0 0 * * ?")
    public void clean(){
        List<Book> books = bookRepository.findAll();
        LocalDate localDate = LocalDate.now();
        for(Book book: books){
            if(book.getEviction().isBefore(localDate)){
                bookRepository.delete(book);
            }
        }
    }
}
