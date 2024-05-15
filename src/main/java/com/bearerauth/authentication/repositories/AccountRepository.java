package com.bearerauth.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bearerauth.authentication.entities.User;

public interface AccountRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
