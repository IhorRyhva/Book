package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRepository;
import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.book.data.DateOfBook;
import com.mtFirstProject.Booking.hotelAndRoom.HotelResponse;
import com.mtFirstProject.Booking.hotelAndRoom.RoomResponse;
import com.mtFirstProject.Booking.payment.PaymentRequest;
import com.mtFirstProject.Booking.payment.PaymentResponse;
import com.mtFirstProject.Booking.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class Helper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public String getMessage(String eviction, String settlement, PaymentResponse payment, RoomResponse room, HotelResponse hotel) {
        return String.format("You book from %s to %s, paid %n, room %n (%s), in hotel %s",
                settlement, eviction,payment.value(), room.number(), room.rate().name(), hotel.name());
    }


    public BookResponse getBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow();/**TODO**/
        return bookMapper.toResponse(book);
    }

    public void remove(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getAllBookForRoom(Integer id) {
        return bookRepository.findBooksByRoomId(id)
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getFreeInTheseDays(DateOfBook dateOfBook) {
        LocalDate settlement = dateOfBook.settlement();
        LocalDate eviction = dateOfBook.eviction();

        List<BookResponse> free = new ArrayList<>();
        for(Book book: bookRepository.findAll()){
            if(
                    book.getBusyDays().contains(settlement) ||
                    book.getBusyDays().contains(eviction) ||
                    book.getSettlement().isEqual(settlement) ||
                    book.getEviction().isEqual(eviction)
            ){
                continue;
            }else{
                free.add(bookMapper.toResponse(book));
            }
        }
        return free;
    }
}
