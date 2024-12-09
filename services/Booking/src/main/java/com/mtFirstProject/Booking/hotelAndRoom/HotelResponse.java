package com.mtFirstProject.Booking.hotelAndRoom;


import com.mtFirstProject.Booking.hotelAndRoom.enums.Stars;

import java.util.List;

public record HotelResponse(
        Integer id,
        Stars stars,
        String location,
        List<RoomResponse> rooms,
        String name
) {
}
