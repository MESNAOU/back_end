package com.example.demo.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
   @Autowired
    TopicService TopicsSer;


   @CrossOrigin(origins = "http://localhost:5173")
   @RequestMapping("/Topics")
    public List<Topics> findALL(){
       return this.TopicsSer.getAllTopics();
   }

   @RequestMapping("/Topics/{id}")
    public Optional<Topics> findOne(@PathVariable("id") Integer id){
       return  this.TopicsSer.geSingleTopics(id);
   }

   @PostMapping("/Topics")
    public void CreateTopic(@RequestBody Topics T){




       this.TopicsSer.CreateTopic(T);
   }
    @PutMapping("/Topics/{id}")
    public void UpdateTopic(@RequestBody Topics T,@PathVariable("id") Integer id){
        this.TopicsSer.UpdateTopics(T,id);
    }
    @DeleteMapping("/Topics/{id}")
    public void Delete(@PathVariable("id") Integer id){
        this.TopicsSer.DeleteTopic(id);
    }
    @DeleteMapping("/Topics/DeleteName/{name}")
    public void DeleteName(@PathVariable("name") String id){
        this.TopicsSer.DeleteTopicName(id);
    }









}
