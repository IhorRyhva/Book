package com.mtFirstProject.Booking.hotelAndRoom;

import com.mtFirstProject.Booking.hotelAndRoom.enums.Rate;
import lombok.Builder;

@Builder
public record RoomResponse(
        int id,
        int number,
        String hotel_name,
        String location,
        Rate rate,
        int numberOfBed,
        int worthPerNight
) {
}
