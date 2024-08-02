package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    public List<Question> findByLiveId(int id);
}
