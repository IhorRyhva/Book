package com.firstProject.Hotel.Model.Room;

import com.firstProject.Hotel.Model.Hotel.HotelDb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "hotelDb")
public class RoomDb {
    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated
    @NotNull
    private Rate rate;

    @Positive
    private int numberOfBed;

    @Positive
    private int worthPerNight;
    @Positive
    private int number;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelDb hotelDb;
}
