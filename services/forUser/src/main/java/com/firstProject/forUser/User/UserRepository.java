package com.firstProject.forUser.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsById(int id);
    Boolean existsByEmail(String email);
    Void deleteAllByEmail(String email);
    Optional<User> findByEmail(String email);
}
