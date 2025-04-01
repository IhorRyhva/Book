package com.firstProject.Hotel.Model.Room;

import com.firstProject.Hotel.Model.Hotel.HotelDb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomRequest(
        @Enumerated
        @NotNull
        Rate rate,
        @Positive
        int numberOfBed,
        @Positive
        int worthPerNight,
        @Positive
        int number
) {
}
