package com.firstProject.Hotel.Model.Room;

import com.firstProject.Hotel.Model.Hotel.HotelDb;
import com.firstProject.Hotel.Model.Hotel.HotelRepository;
import com.firstProject.Hotel.Model.Hotel.HotelResponse;
import com.firstProject.Hotel.Model.Hotel.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomMapper mapper;
    private final HotelService hotelService;
    public List<RoomResponse> getAllRoomsByHotelId(Integer hotel_id) {
        return hotelService
                .getById(hotel_id)
                .getRooms()
                .stream()
                .map(mapper::toRoomResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse getRoom(Integer number, String hotelName) {
        HotelResponse hotel = hotelService.findByName(hotelName);
        List<RoomResponse> roomResponses = hotel.rooms();
        System.out.println(roomResponses.get(number - 1));
        return roomResponses.get(number - 1);
    }
}
