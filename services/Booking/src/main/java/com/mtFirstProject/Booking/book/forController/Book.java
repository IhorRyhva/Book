package com.mtFirstProject.Booking.book.forController;

import com.mtFirstProject.Booking.book.data.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    private String hotel_name;
    private Integer roomId;
    private String userEmail;
    @Enumerated
    PaymentType paymentType;

    private LocalDate settlement;
    private LocalDate eviction;
    @ElementCollection
    private List<LocalDate> busyDays = new ArrayList<>();

    public void updateBusyDays(){
        this.busyDays = findBusyDays(this.settlement, this.eviction);
    }
    private List<LocalDate> findBusyDays(LocalDate settlement, LocalDate eviction) {
        List<LocalDate> busyDays = new ArrayList<>();
        LocalDate tempSettlement = settlement;
        LocalDate tempEviction = eviction;

        while (tempSettlement.isBefore(tempEviction)){
            tempSettlement = tempSettlement.plusDays(1);
            busyDays.add(tempSettlement);
        }
        return busyDays;
    }
}
