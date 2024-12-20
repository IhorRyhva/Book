package com.mtFirstProject.Booking.book;

import com.mtFirstProject.Booking.hotelAndRoom.HotelClient;
import com.mtFirstProject.Booking.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final HotelClient hotelClient;
    private final UserClient userClient;
//    private final BookProducer producer;

    @Transactional
    public BookResponse createBook(BookRequest request) {
        var hotel = this.hotelClient.getHotelByName(request.hotel_name()).orElseThrow();/**TODO**/
        var room = this.hotelClient.getRoom(request.roomId(), hotel.name()).orElseThrow();
        var user = userClient.findByName(request.userEmail()).orElseThrow();

//        producer.sendBookConfirmation(
//                BookConfirmation.builder()
//                        .userEmail(user.email())
//                        .hotelName(hotel.name())
//                        .roomNumber(room.number())
//                        .settlement(request.settlement())
//                        .eviction(request.eviction())
//                        .build()
//        );
        return getBookResponse(request);
    }

    private BookResponse getBookResponse(BookRequest request) {
        if(request.eviction().isBefore(request.settlement())
                || request.settlement().isBefore(LocalDate.now())){
            throw new RuntimeException();/**TODO**/
        }
        List<Book> allRoomsBook = bookRepository.findBooksByRoomId(request.roomId());
        if(bookMapper.dateIsFree(allRoomsBook, request)){
            Book book = bookMapper.toBook(request);
            bookRepository.save(book);
            return bookMapper.toResponse(book);
        }else {
            throw new RuntimeException();/**TODO**/
        }
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
}
