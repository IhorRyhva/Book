package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {
    public boolean dateIsFree(List<Book> books, BookRequest request) {
        List<LocalDate> settlement = books.stream().map(book -> book.getSettlement())
                .collect(Collectors.toList());
        List<LocalDate> eviction = books.stream().map(book -> book.getEviction())
                .collect(Collectors.toList());
        List<LocalDate> busyDay = getAllBusyDay(books);
        if(settlement.contains(request.settlement())
                || eviction.contains(request.eviction())
                || busyDay.contains(request.eviction())
                || busyDay.contains(request.settlement())){
            return false;
        }
        return true;
    }

    private List<LocalDate> getAllBusyDay(List<Book> books) {
        List<LocalDate>  busyDays = new ArrayList<>();
        List<List<LocalDate>> temp = books.stream().map(book -> book.getBusyDays()).collect(Collectors.toList());
        for(List<LocalDate> list: temp){
            busyDays.addAll(list);
        }
        return busyDays;
    }
    public Book toBook(BookRequest request) {
        Book book = Book.builder()
                .roomId(request.roomNumber())
                .userEmail(request.userEmail())
                .hotel_name(request.hotel_name())
                .settlement(request.settlement())
                .eviction(request.eviction())
                .build();
        book.updateBusyDays();
        return book;
    }

    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .userEmail(book.getUserEmail())
                .roomId(book.getRoomId())
                .eviction(book.getEviction())
                .settlement(book.getSettlement())
                .hotel_name(book.getHotel_name())
                .build();
    }
    /**For test**/
//    public static void main(String[] args) {
//        System.out.println(new BookMapper().dateIsFree(list(), reqeust()));
//    }
//
//    private static BookRequest reqeust() {
//        return BookRequest.builder()
//                .userName("Krasava")
//                .eviction(LocalDate.now().plusDays(15))
//                .settlement(LocalDate.now().plusDays(10))
//                .hotel_name("Hotel")
//                .roomId(5)
//                .userEmail("email@gmail.com")
//                .build();
//    }
//
//    private static List<Book> list() {
//        List<Book> books = new ArrayList<>();
//        LocalDate localDate = LocalDate.now();
//        for (int i = 1; i < 6; i++) {
//            localDate = localDate.plusDays(5);
//            Book book = Book.builder()
//                    .id(i)
//                    .roomId(5)
//                    .userEmail("User" + i)
//                    .hotel_name("Hotel")
//                    .settlement(localDate)
//                    .eviction(localDate.plusDays(3))
//                    .build();
//            book.updateBusyDays();
//            books.add(book);
//        }
//        return books;
//    }
}
