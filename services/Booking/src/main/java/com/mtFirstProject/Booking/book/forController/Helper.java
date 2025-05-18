package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRepository;
import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.book.data.DateOfBook;
import com.mtFirstProject.Booking.hotelAndRoom.HotelClient;
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
    private final HotelClient hotelClient;
    public String getMessage(String eviction, String settlement, Integer value, RoomResponse room, HotelResponse hotel) {
        return String.format("You book from %s to %s, paid %n, room %n (%s), in hotel %s",
                settlement, eviction, value, room.number(), room.rate().name(), hotel.name());
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

    public List<HotelResponse> getFreeInTheseDays(DateOfBook dateOfBook) {
        LocalDate settlement = dateOfBook.settlement();
        LocalDate eviction = dateOfBook.eviction();
        System.out.println(eviction);

        List<HotelResponse> allHotels = hotelClient.getAllHotels().orElseThrow();
        System.out.println("Ok");

        for(Book book: bookRepository.findAll()){
            if(
                    book.getBusyDays().contains(settlement) ||
                    book.getBusyDays().contains(eviction) ||
                    book.getSettlement().isEqual(settlement) ||
                    book.getEviction().isEqual(eviction)
            ){
                allHotels
                        .get(hotelClient
                                .getHotelByName(book.getHotel_name())
                                .get()
                                .id())
                        .rooms()
                        .remove(book
                                .getRoomId());
            }
        }


        return allHotels;
    }
}
