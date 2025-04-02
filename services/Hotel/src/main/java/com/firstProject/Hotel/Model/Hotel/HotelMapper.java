package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelMapper {
    private final RoomRepository roomRepository;
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
    public HotelDb createHotel(HotelRequest request, List<RoomRequest> rooms) {
        HotelDb hotelDb = HotelDb.builder()
                .stars(request.stars())
                .name(request.name())
                .location(request.location())
                .build();
        List<RoomDb> roomDbs = toDb(rooms, hotelDb);
        hotelDb.setRooms(roomDbs);
        return hotelDb;
    }

    private List<RoomDb> toDb(List<RoomRequest> rooms, HotelDb hotelDb) {
        List<RoomDb> roomDbs = new ArrayList<>();
        for(RoomRequest room: rooms){
             roomDbs.add(RoomDb.builder()
                    .number(room.number())
                    .worthPerNight(room.worthPerNight())
                    .numberOfBed(room.numberOfBed())
                    .rate(room.rate())
                    .hotelDb(hotelDb)
                    .build());
        }
        return roomDbs;
    }
}
