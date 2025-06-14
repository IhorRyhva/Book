package com.mtFirstProject.Booking.book.list.Data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;

    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ListOfBookslInfo> books;
}
