package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Jeune;

public interface JeuneRepository extends JpaRepository<Jeune,Long> {
}
