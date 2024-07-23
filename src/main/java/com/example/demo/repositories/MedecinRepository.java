package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Medecin;
import com.example.demo.entities.Responsable;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    boolean existsByCin(String cin);
    boolean existsByInpe(String inpe);
    boolean existsByPpr(String ppr);
}
