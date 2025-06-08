package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.BookRepository;
import com.mtFirstProject.Booking.book.data.BookResponse;
import com.mtFirstProject.Booking.book.data.ForSort;
import com.mtFirstProject.Booking.hotelAndRoom.HotelClient;
import com.mtFirstProject.Booking.hotelAndRoom.HotelResponse;
import com.mtFirstProject.Booking.hotelAndRoom.RoomResponse;
import com.mtFirstProject.Booking.hotelAndRoom.enums.Rate;
import com.mtFirstProject.Booking.hotelAndRoom.enums.Stars;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class Helper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final HotelClient hotelClient;
    public String getMessage(String eviction, String settlement, int value, RoomResponse room, HotelResponse hotel) {
        return String.format("You book from %s to %s, paid %d, room %d (%s), in hotel %s",
                settlement, eviction, value, room.number(), room.rate().name(), hotel.name());
    }


    public BookResponse getBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow();/**TODO**/
        return bookMapper.toResponse(book);
    }

    public void remove(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getAllBookForRoom(Integer id) {
        return bookRepository.findBooksByRoomId(id)
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<HotelResponse> chooseHotelAndRoomForSort(ForSort forSort) {
        HashMap<String, HotelResponse> hotelsMap = new HashMap<>();
        List<HotelResponse> hotelsList;

        /**location**/
        if(forSort.location() != null && !forSort.location().isEmpty()){
            hotelsList = hotelClient.getHotelsByLocation(forSort.location()).get();
        }else{
            hotelsList = hotelClient.getAllHotels().get();
        }

        /**stars**/
        if(forSort.stars() != null){
            sortForStars(hotelsList, forSort.stars());
        }

        /**rate**/
        if(forSort.rate() != null){
            sortForRate(hotelsList, forSort.rate());
        }

        /**numberOfBed**/
        if(forSort.numberOfBed() != 0){
            sortForNumberOfBed(hotelsList, forSort.numberOfBed());
        }

        /**byPrice**/
        if(
                forSort.maxWorthPerNight() >= forSort.minWorthPerNight() &&
                forSort.maxWorthPerNight() > 0 &&
                forSort.minWorthPerNight() >= 0
        ){
            sortForWorth(hotelsList, forSort.maxWorthPerNight(), forSort.minWorthPerNight());
        }

        /**byDate**/
        if(
                forSort.settlement() != null &&
                forSort.eviction() != null &&
                forSort.settlement().isBefore(forSort.eviction()) &&
                LocalDate.now().isBefore(forSort.settlement())

        ) {
            hotelsMap = getFreeRooms(forSort, toMap(hotelsList));
        }else{
            /**Say that date is incorect**/
            return hotelsList;
        }

        return toList(hotelsMap);
    }

    private void sortForWorth(List<HotelResponse> hotelsList, Integer maxWorthPerNight, Integer minWorthPerNight) {
        for(HotelResponse hotelResponse: hotelsList){
            hotelResponse.rooms().removeIf(
                    roomResponse -> roomResponse.worthPerNight() > maxWorthPerNight
                                    || roomResponse.worthPerNight() < minWorthPerNight
            );
        }
    }

    private void sortForNumberOfBed(List<HotelResponse> hotelsList, Integer numberOfBed) {
        for(HotelResponse hotelResponse: hotelsList){
            hotelResponse.rooms().removeIf(roomResponse -> roomResponse.numberOfBed() != numberOfBed);
        }
    }

    private void sortForRate(List<HotelResponse> hotelsList, Rate rate) {
        for(HotelResponse hotelResponse: hotelsList){
            hotelResponse.rooms().removeIf(roomResponse -> roomResponse.rate() != rate);
        }
    }

    private void sortForStars(List<HotelResponse> hotelsList, Stars stars) {
        hotelsList.removeIf(hotelResponse -> hotelResponse.stars() != stars);
    }

    private HashMap<String, HotelResponse> getFreeRooms(ForSort forSort, HashMap<String, HotelResponse> hotels) {
        LocalDate settlement = forSort.settlement();
        LocalDate eviction = forSort.eviction();

        for(Book book: bookRepository.findAll()){
            if(
                    book.getBusyDays().contains(settlement) ||
                    book.getBusyDays().contains(eviction) ||
                    book.getSettlement().isEqual(settlement) ||
                    book.getEviction().isEqual(eviction)
            ){
                if(hotels.containsKey(book.getHotel_name()))
                    hotels.get(book.getHotel_name()).rooms().remove(book.getRoomId() - 1);
            }
        }
        return hotels;
    }

    private List<HotelResponse> toList(HashMap<String, HotelResponse> hotels) {
        if(hotels.size() == 0){
            return new ArrayList<>();
        }

        List<HotelResponse> books = new ArrayList<>();
        for(HotelResponse hotelResponse: hotels.values()){
            books.add(hotelResponse);
        }
        return books;
    }

    private HashMap<String, HotelResponse> toMap(List<HotelResponse> allHotels) {
        HashMap<String, HotelResponse> map = new HashMap<>();
        System.out.println("line176" + allHotels.size());
        for(HotelResponse hotelResponse: allHotels){
            map.put(hotelResponse.name(), hotelResponse);
        }
        System.out.println(map.size());
        return map;
    }


}
