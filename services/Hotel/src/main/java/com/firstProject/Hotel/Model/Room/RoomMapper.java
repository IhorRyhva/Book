package com.firstProject.Hotel.Model.Room;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomMapper {
    public RoomResponse toRoomResponse(RoomDb roomDb) {
        return RoomResponse.builder()
                .hotel_name(roomDb.getHotelDb().getName())
                .id(roomDb.getId())
                .number(roomDb.getNumber())
                .location(roomDb.getHotelDb().getLocation())
                .numberOfBed(roomDb.getNumberOfBed())
                .rate(roomDb.getRate())
                .worthPerNight(roomDb.getWorthPerNight())
                .build();
    }

}
