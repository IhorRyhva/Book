package com.firstProject.Hotel.Model.Room;

import jakarta.validation.constraints.Positive;
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
