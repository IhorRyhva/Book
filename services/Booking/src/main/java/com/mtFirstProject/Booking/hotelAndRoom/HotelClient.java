package com.mtFirstProject.Booking.hotelAndRoom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "hotel-microservice",
        url = "${spring.config.hotel-url}"
)
public interface HotelClient {
    @GetMapping("{hotel}")
    Optional<HotelResponse> getHotelByName(@PathVariable("hotel") String hotel);
    @GetMapping("{hotel-name}/rooms/{Id}")
    Optional<RoomResponse> getRoom(@PathVariable("Id") Integer number, @PathVariable("hotel-name") String hotelName);
    @GetMapping("rooms")
    Optional<List<RoomResponse>> getAllHotelsRooms(@RequestParam int hotel_id);
}
