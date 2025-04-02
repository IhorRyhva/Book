package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomDb;
import com.firstProject.Hotel.Model.Room.RoomRequest;

import java.util.List;

public record HotelRequest(
       Stars stars,
       String location,
       String name
) {
}
