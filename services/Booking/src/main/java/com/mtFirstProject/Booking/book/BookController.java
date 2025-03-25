package com.mtFirstProject.Booking.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/books/")
public class BookController {
    private final BookService service;

    @PostMapping("create")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {
        return ResponseEntity.ok(service.createBook(request));
    }
    @PreAuthorize("hasRole='client-admin'")
    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getBook(id));
    }
    @GetMapping("all")
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @PreAuthorize("hasRole='client-admin'")
    @GetMapping("all/{room-id}")
    public ResponseEntity<List<BookResponse>> getAllBookForRoom(@PathVariable("room-id") Integer id) {
        return ResponseEntity.ok(service.getAllBookForRoom(id));
    }
    @PreAuthorize("hasRole='client-admin'")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.remove(id);
        return ResponseEntity.accepted().build();
    }
}
