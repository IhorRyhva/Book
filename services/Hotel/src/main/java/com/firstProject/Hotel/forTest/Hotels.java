package com.firstProject.Hotel.forTest;

import com.firstProject.Hotel.Model.Hotel.HotelDb;
import com.firstProject.Hotel.Model.Hotel.HotelRepository;
import com.firstProject.Hotel.Model.Hotel.Stars;
import com.firstProject.Hotel.Model.Room.Rate;
import com.firstProject.Hotel.Model.Room.RoomDb;
import com.firstProject.Hotel.Model.Room.RoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Component
public class Hotels {
    private final HotelRepository hotelRepository;
    public void create(){
        for (int i = 1; i <= 10; i++) {
            HotelDb hotelDb = getHotel(i, "tempName" + i);
            List<RoomDb> rooms = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                RoomDb roomDb = getRoom(getId(i, j), j, hotelDb);
                rooms.add(roomDb);
            }
            hotelDb.setRooms(rooms);
            hotelRepository.save(hotelDb);
        }
    }

    private int getId(int i, int j) {
        int id;
        if(i < 2){
            id = j;
        }else{
            id = j + i *100 - 100;
        }
        return id;
    }

    private RoomDb getRoom(int id, int number, HotelDb hotelDb) {
        Rate rate = getRateForRoom();
        return RoomDb.builder()
                .hotelDb(hotelDb)
                .number(number)
                .rate(rate)
                .numberOfBed(getBeds(rate))
                .worthPerNight(getWorth(rate))
                .build();
    }

    private int getWorth(Rate rate) {
        switch (rate){
            case LUX -> {
                return 2000;
            }
            case FAMILY -> {
                return 1500;
            }
            case ECONOMIC -> {
                return 1000;
            }
            case PRESIDENT -> {
                return 3000;
            }
            default -> {
                return 500;
            }
        }
    }

    private int getBeds(Rate rate) {
        switch (rate){
            case LUX -> {
                return 2;
            }
            case FAMILY -> {
                return 4;
            }
            case ECONOMIC -> {
                return 1;
            }
            case PRESIDENT -> {
                return 3;
            }
            default -> {
                return 5;
            }
        }
    }

    private Rate getRateForRoom() {
        int random = ThreadLocalRandom.current().nextInt(1, 5);
        return switch (random) {
            case 1 -> Rate.ECONOMIC;
            case 2 -> Rate.FAMILY;
            case 3 -> Rate.LUX;
            case 4 -> Rate.PRESIDENT;
            default -> Rate.ECONOMIC;
        };
    }


    private HotelDb getHotel(int i, String name) {
        return HotelDb.builder()
                .location("Ukraine")
                .name(name)
                .stars(getStars(i))
                .build();
    }

    private Stars getStars(int i) {
        while (i > 5){
            i--;
        }
        switch (i){
            case(1):
                return Stars.ONE;
            case (2):
                return Stars.TWO;
            case (3):
                return Stars.THREE;
            case (4):
                return Stars.FOUR;
            default:
                return Stars.FIVE;
        }
    }
}
