package com.firstProject.Hotel.Model.Hotel;

import com.firstProject.Hotel.Model.Room.RoomDb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "rooms")
public class HotelDb {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated
    private Stars stars;
    @NotNull
    private String location;
    @OneToMany(mappedBy = "hotelDb",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoomDb> rooms;
    @NotNull
    @Column(unique = true)
    private String name;
}
