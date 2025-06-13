package com.firstProject.Hotel.Controllers;

import com.firstProject.Hotel.Model.Hotel.*;
import com.firstProject.Hotel.Model.Room.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels/")
@RequiredArgsConstructor
@Tag(name = "Book")
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
    public ResponseEntity<Integer> createHotel(@RequestBody FullHotelRequest fullHotelRequest){
        System.out.println(fullHotelRequest.request());
        return ResponseEntity.ok(hotelService.create(fullHotelRequest));
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomResponse>> getAllHotelsRooms(@RequestParam int hotel_id){
        return ResponseEntity.ok(roomService.getAllRoomsByHotelId(hotel_id));
    }
    @GetMapping("{hotel-name}/rooms/{number}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable("number") Integer number,
                                                @PathVariable("hotel-name") String hotelName){
        return ResponseEntity.ok(roomService.getRoom(number, hotelName));
    }
    @GetMapping("location")
    public ResponseEntity<List<HotelResponse>> getHotelsByLocation(@RequestParam String location){
        return ResponseEntity.ok(hotelService.getByLocation(location));
    }

}
