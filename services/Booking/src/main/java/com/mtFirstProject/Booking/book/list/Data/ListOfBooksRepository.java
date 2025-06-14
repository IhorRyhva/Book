package com.mtFirstProject.Booking.book.list.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfBooksRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByEmail(String email);
    Boolean existsByEmail(String email);
}
