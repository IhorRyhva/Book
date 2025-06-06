package com.mtFirstProject.Booking.book.data;

import com.mtFirstProject.Booking.book.forController.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByRoomId(Integer room_id);
}
