package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.book.data.ForSort;
import com.mtFirstProject.Booking.hotelAndRoom.HotelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/books/")
public class BookController {
    private final BookService service;
    private final Helper helper;

    @PostMapping("create")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {
        return ResponseEntity.ok(service.createBook(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(helper.getBook(id));
    }

    //forTest
    @GetMapping("all")
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(helper.getAll());
    }

    @GetMapping("all/{room-id}")
    public ResponseEntity<List<BookResponse>> getAllBookForRoom(@PathVariable("room-id") Integer id) {
        return ResponseEntity.ok(helper.getAllBookForRoom(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        helper.remove(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("search")
    public ResponseEntity<List<HotelResponse>> searchFree(@RequestBody ForSort forSort){
        System.out.println(forSort.settlement());
        return ResponseEntity.ok(helper.chooseHotelAndRoomForSort(forSort));
    }
}
