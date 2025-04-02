package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FullHotelRequest(
        @NotNull
        HotelRequest request,
        @NotNull
        List<RoomRequest> rooms
) {
}
