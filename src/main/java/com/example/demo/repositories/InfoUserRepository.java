package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.InfoUser;

public interface InfoUserRepository extends JpaRepository<InfoUser,Long> {
    Optional<InfoUser> findByMail(String mail);
    boolean existsByMail(String mail);
}

