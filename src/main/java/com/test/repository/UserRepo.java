package com.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhonenumber(String phonenumber);
}
