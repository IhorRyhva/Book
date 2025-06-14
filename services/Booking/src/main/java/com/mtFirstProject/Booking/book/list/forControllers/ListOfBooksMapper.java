package com.mtFirstProject.Booking.book.list.forControllers;

import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.list.Data.UserInfo;
import com.mtFirstProject.Booking.book.list.Data.ListOfBookslInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ListOfBooksMapper {
    public ListOfBooksResponse toResponse(ListOfBookslInfo book){
        return ListOfBooksResponse.builder()
                .settlement(book.getSettlement())
                .eviction(book.getEviction())
                .hotel_name(book.getHotel_name())
                .roomNumber(book.getRoomNumber())
                .build();
    }

    public ListOfBookslInfo toBookInfo(BookRequest request, UserInfo user) {
        return ListOfBookslInfo.builder()
                .username(user)
                .eviction(request.eviction())
                .hotel_name(request.hotel_name())
                .settlement(request.settlement())
                .roomNumber(request.roomNumber())
                .build();
    }

    public List<ListOfBookslInfo> getList(UserInfo user, BookRequest request) {
        return Arrays.asList(toBookInfo(request, user));
    }
}
