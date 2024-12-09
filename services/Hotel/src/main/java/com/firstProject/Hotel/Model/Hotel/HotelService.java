package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomRepository;
import com.firstProject.Hotel.exception.HotelExistException;
import com.firstProject.Hotel.forTest.Hotels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository repository;
    private final HotelMapper mapper;
    public List<HotelResponse> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public Integer create(HotelRequest request) {
        if(repository.existsByName(request.name())){
            throw new HotelExistException();
        }
        HotelDb hotelDb = mapper.createHotel(request);
        return repository.save(hotelDb).getId();
    }
    public HotelDb getById(int id){
        Optional<HotelDb> hotelDb = repository.findById(id);
        if(hotelDb == null){
            /**TODO**/
        }
        return hotelDb.get();
    }

    public HotelResponse findByName(String hotel) {
        return mapper.toResponse(repository.findByName(hotel));
    }
}
