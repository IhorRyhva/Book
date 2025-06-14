package com.mtFirstProject.Booking.book.list.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOfBookslInfo {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate settlement;

    private LocalDate eviction;

    private String hotel_name;

    private Integer roomNumber;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserInfo username;
}
