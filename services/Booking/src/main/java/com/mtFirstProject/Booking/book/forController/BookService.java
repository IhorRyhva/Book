package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRepository;
import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.book.data.DateOfBook;
import com.mtFirstProject.Booking.book.helpers.EmailService;
import com.mtFirstProject.Booking.hotelAndRoom.HotelClient;
import com.mtFirstProject.Booking.hotelAndRoom.HotelResponse;
import com.mtFirstProject.Booking.hotelAndRoom.RoomResponse;
import com.mtFirstProject.Booking.payment.PaymentClient;
import com.mtFirstProject.Booking.payment.PaymentRequest;
import com.mtFirstProject.Booking.payment.PaymentResponse;
import com.mtFirstProject.Booking.payment.PaymentService;
import com.mtFirstProject.Booking.user.UserClient;
import com.mtFirstProject.Booking.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final HotelClient hotelClient;
    private final UserClient userClient;
    private final PaymentClient paymentClient;
    private final PaymentService paymentService;
    private final EmailService emailService;
    private final Helper helper;
    DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private final BookProducer producer;

    @Transactional
    public BookResponse createBook(BookRequest request) {
        var hotel = this.hotelClient.getHotelByName(request.hotel_name()).orElseThrow();/**TODO**/
        System.out.println(hotel.name());

        var room = this.hotelClient.getRoom(request.roomId(), hotel.name()).orElseThrow();
        System.out.println(room.number());

        var user = userClient.findByName(request.userEmail()).orElseThrow();
        System.out.println(user.firstName());

        Book book = saveBook(request);
        String eviction = CUSTOM_FORMATTER.format(book.getEviction());
        String settlement = CUSTOM_FORMATTER.format(book.getSettlement());

        var payment = paymentClient.createPayment(getPaymentRequest(request, room, book, user)).getBody();
        bookRepository.save(book);
        String message = helper.getMessage(eviction, settlement, payment, room, hotel);
        emailService.sendEmail(user.email(), "Booked", message);
        return bookMapper.toResponse(book);
    }

    //get PaymentRequest
    private PaymentRequest getPaymentRequest(BookRequest request, RoomResponse room, Book book, UserResponse user) {
        return paymentService.create(room.worthPerNight(), book.getBusyDays().size() + 1, user.email(), 0, request.paymentType());
    }
    //ForSave
    private Book saveBook(BookRequest request) {
        if(request.eviction().isBefore(request.settlement())
                || request.settlement().isBefore(LocalDate.now())){
            throw new RuntimeException();/**TODO**/
        }
        List<Book> allRoomsBook = bookRepository.findBooksByRoomId(request.roomId());
        if(bookMapper.dateIsFree(allRoomsBook, request)){
            Book book = bookMapper.toBook(request);
            return book;
        }else {
            throw new RuntimeException();/**TODO**/
        }
    }

}
