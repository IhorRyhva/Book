package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomDb;
import com.firstProject.Hotel.Model.Room.RoomMapper;
import com.firstProject.Hotel.Model.Room.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelMapper {
    public HotelResponse toResponse(HotelDb db) {
        RoomMapper roomMapper = new RoomMapper();
        return HotelResponse.builder()
                .rooms(db.getRooms().stream().map(roomMapper::toRoomResponse).collect(Collectors.toList()))
                .stars(db.getStars())
                .name(db.getName())
                .id(db.getId())
                .location(db.getLocation())
                .build();
    }
    public HotelDb createHotel(HotelRequest request) {
        return HotelDb.builder()
                .rooms(request.rooms())
                .stars(request.stars())
                .name(request.name())
                .location(request.location())
                .build();
    }
}
