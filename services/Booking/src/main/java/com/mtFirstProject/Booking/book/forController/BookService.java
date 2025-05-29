package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRepository;
import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.hotelAndRoom.HotelClient;
import com.mtFirstProject.Booking.payment.PaymentClient;
import com.mtFirstProject.Booking.payment.PaymentRequest;
import com.mtFirstProject.Booking.payment.PaymentService;
import com.mtFirstProject.Booking.user.UserClient;
import com.mtFirstProject.Booking.user.UserResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final HotelClient hotelClient;
    private final UserClient userClient;
    private final PaymentClient paymentClient;
    private final PaymentService paymentService;
    private final Helper helper;
    DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private final BookProducer producer;

    @Transactional
    public BookResponse createBook(BookRequest request) {
        var hotel = this.hotelClient.getHotelByName(request.hotel_name()).orElseThrow();/**TODO**/
        System.out.println(hotel.name());

        var room = this.hotelClient.getRoom(request.roomNumber(), hotel.name()).orElseThrow();
        System.out.println(room.number());

        UserResponse user;
        try {
            user = userClient.findByName(request.userEmail()).orElseThrow();
        }catch (FeignException e){
            user = new UserResponse(0, "unknownUser", "unknownUser", request.userEmail());
        }

        System.out.println(user.firstName());

        Book book = saveBook(request);
        String eviction = CUSTOM_FORMATTER.format(book.getEviction());
        String settlement = CUSTOM_FORMATTER.format(book.getSettlement());

        int value = (book.getBusyDays().size() + 1) * room.worthPerNight();
        String message = helper.getMessage(eviction, settlement, value, room, hotel);
        var payment = getPaymentRequest(request, value, user, message);

        System.out.println(payment.paymentType() + payment.message() + payment.value() + payment.cardNumber() + payment.email());
        paymentClient.createPayment(payment);
        bookRepository.save(book);
        return bookMapper.toResponse(book);
    }

    //get PaymentRequest
    private PaymentRequest getPaymentRequest(BookRequest request, Integer value, UserResponse user, String message) {
        return paymentService.create(value, user.email(), 0, request.paymentType(), message);
    }
    //ForSave
    private Book saveBook(BookRequest request) {
        if(request.eviction().isBefore(request.settlement())
                || request.settlement().isBefore(LocalDate.now())){
            throw new RuntimeException();/**TODO**/
        }
        List<Book> allRoomsBook = bookRepository.findBooksByRoomId(request.roomNumber());
        if(bookMapper.dateIsFree(allRoomsBook, request)){
            Book book = bookMapper.toBook(request);
            return book;
        }else {
            throw new RuntimeException();/**TODO**/
        }
    }

}
