package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Administrateur;

import java.util.Optional;

public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {
    /*public Optional<Administrateur> findByMdp(String mdp );
    public Optional<Administrateur>  findByEmail(String e);*/
}
