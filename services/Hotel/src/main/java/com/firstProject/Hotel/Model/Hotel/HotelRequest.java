package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomDb;

import java.util.List;

public record HotelRequest(
       Stars stars,
       String location,
       List<RoomDb> rooms,
       String name
) {
}
