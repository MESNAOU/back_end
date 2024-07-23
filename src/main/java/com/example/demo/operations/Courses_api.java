package com.example.demo.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Courses_api {

    @Autowired
    CourseService SERVER;

    @RequestMapping("/Topics/Courses")
    public List<Courses> getAllcourses(){
        return  this.SERVER.getAllCourses();
    }

    @RequestMapping("/Topics/Courses/{id}")
    public Optional<Courses> getSingleCourse(@PathVariable("id") Integer id){
        Courses C=this.SERVER.getSingleCourseByIdofCourse(id).get();
        System.out.println(C.getTopic().getTitle());

        return this.SERVER.getSingleCourseByIdofCourse(id);
    }

    @RequestMapping("/Topics/{id}/Courses")
public List<Courses> getAllCoursesByTopicId(@PathVariable("id") Integer id){
        return this.SERVER.getAllCourseByIdofTopic(id);
    }

    @RequestMapping("/Topics/TopicsName/{id}/Courses")
    public List<Courses> getAllCoursesByTopicName(@PathVariable("id") String id){
        return this.SERVER.getAllCourseByNameofTopic(id);
    }

    @PostMapping("/Topics/{id}/Courses")
    public void SaveCourse(@RequestBody Courses C,@PathVariable("id") Integer id){
        C.setTopic(new Topics(id,""));
        this.SERVER.SaveCourse(C);

    }
    @PutMapping("/Topics/{id}/Courses")
    public void UpdateCourse(@RequestBody Courses C,@PathVariable("id") Integer id){
        C.setTopic(new Topics(id,""));
        this.SERVER.UpdateCourse(C);
    }
    @DeleteMapping("/Topics/Courses/{id}")
    public void DeleteCourse(@PathVariable("id") Integer  id){
        this.SERVER.DeleteCourse(id);
    }









}
