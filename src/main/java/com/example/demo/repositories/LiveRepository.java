package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Live;

import java.util.List;
import java.util.Optional;

public interface LiveRepository extends JpaRepository<Live,Integer> {
     public List<Live> findByAdminId(int id);
     public Optional<Live> findBySubject(String S);
     //public List<Live> findByResponsableId(Long id);
     public  List<Live> findByResponsableId(Long id);
}

