package com.firstProject.Hotel.Controllers;

import com.firstProject.Hotel.Model.Hotel.*;
import com.firstProject.Hotel.Model.Room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels/")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAll());
    }
    @GetMapping("{hotel}")
    public ResponseEntity<HotelResponse> getHotelByName(@PathVariable("hotel") String hotel){
        return ResponseEntity.ok(hotelService.findByName(hotel));
    }

    @PostMapping("add")
    public ResponseEntity<Integer> createHotel(@RequestBody HotelRequest request){
        return ResponseEntity.ok(hotelService.create(request));
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomResponse>> getAllHotelsRooms(@RequestParam int hotel_id){
        return ResponseEntity.ok(roomService.getAllRoomsByHotelId(hotel_id));
    }
    @GetMapping("{hotel-name}/rooms/{Id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable("Id") Integer number,
                                                @PathVariable("hotel-name") String hotelName){
        return ResponseEntity.ok(roomService.getRoom(number, hotelName));
    }

}
