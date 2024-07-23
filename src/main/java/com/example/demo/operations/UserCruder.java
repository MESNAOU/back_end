package com.example.demo.operations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCruder extends JpaRepository<User,Integer> {
    public Optional<User> findByPassword(String password);
}
