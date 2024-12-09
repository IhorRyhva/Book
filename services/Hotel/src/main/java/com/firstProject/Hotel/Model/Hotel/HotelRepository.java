package com.firstProject.Hotel.Model.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HotelRepository extends JpaRepository<HotelDb, Integer> {
    Boolean existsByName(String name);
    @Override
    Optional<HotelDb> findById(Integer integer);
    HotelDb findByName(String name);
}
