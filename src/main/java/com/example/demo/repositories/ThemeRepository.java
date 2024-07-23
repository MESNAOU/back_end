package com.example.demo.repositories;

import com.example.demo.configurations.OperationsWebConfig;
import com.example.demo.entities.Theme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    //public List<PropositionThématique> findByLiveId(int id);
    public Optional<Theme> findByContenu(String s);
}
