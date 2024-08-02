package com.example.demo.controllers;

import com.example.demo.entities.Live;
import com.example.demo.entities.Question;
import com.example.demo.repositories.LiveRepository;
import com.example.demo.service.QuestionService;
import com.example.demo.Exception.LiveNotFoundException;
import com.example.demo.dto.QuestionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService service;
    @Autowired
    LiveRepository liveRepository;

    @GetMapping("/streams/{id}/questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions(@PathVariable int id){
		try {
			List<QuestionDTO> list_questionses = this.service.getAllQuestions(id);
			if (list_questionses.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(list_questionses);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
    /*
    @PostMapping("/streams/{id}/questions")
    public ResponseEntity<String> createOne(@RequestBody Question Q, @PathVariable int id) {
        try {
        	Live l = liveRepository.findById(id)
        			.orElseThrow(() -> new LiveNotFoundException("Le live d'id "+id+" est introvable"));
        	Q.setLive(l);
        	this.service.createOne(Q);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Question");
        }
    }
    */
    @PostMapping("/process")
    public ResponseEntity<String> processQuestions(@RequestBody List<String> questions) {
        String summary = service.processQuestions(questions);
        return ResponseEntity.ok(summary);
    }
}
