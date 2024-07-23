

package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.ProfessionnelSante;
import com.example.demo.entities.Responsable;

public interface ProfessionnelRepository extends JpaRepository<ProfessionnelSante, Long> {
    boolean existsByCin(String cin);
    boolean existsByInpe(String inpe);
}
