package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomDb;
import com.firstProject.Hotel.Model.Room.RoomResponse;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
@Builder
public record HotelResponse(
        Integer id,
        Stars stars,
        String location,
        List<RoomResponse> rooms,
        String name
) {
}
