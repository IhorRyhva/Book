package com.myFirstProject.Payment.paymentData;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private Long id;

    String userName;

    @Enumerated
    PaymentType paymentType;
    int value;
}
