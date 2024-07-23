package com.example.demo.operations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudOPERATIONS extends JpaRepository<Topics,Integer> {
    public void deleteByTitle(String title);
}
