package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.entities.Question;
import com.example.demo.mappers.QuestionMapper;
import com.example.demo.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionMapper mapper;
    public List<QuestionDTO> getAllQuestions(int id){

        return  this.mapper.getallDtoQuestions(this.questionRepository.findByLiveId(id));
    }

    public void createOne(Question Q){

        this.questionRepository.save(Q);
    }


}
