package com.firstProject.forUser.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsById(int id);
    User findByEmail(String email);
}
