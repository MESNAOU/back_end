package com.example.demo.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    @Autowired
    CrudCourses Crud;

    public List<Courses> getAllCourses(){
        List<Courses> L=new ArrayList<>();


        Iterator<Courses> E=this.Crud.findAll().iterator();
        while(E.hasNext()){
            L.add(E.next());
        }
        return L;

    }

    public Optional<Courses> getSingleCourseByIdofCourse(Integer id){
        return this.Crud.findById(id);
    }
    public List<Courses> getAllCourseByIdofTopic(Integer id){
           return  this.Crud.findByTopicId(id);
    }
    public List<Courses> getAllCourseByNameofTopic(String id){
        return  this.Crud.findByTopicTitle(id);
    }

    public void SaveCourse(Courses C){
        this.Crud.save(C);
    }
    public void UpdateCourse(Courses C){
        this.Crud.save(C);
    }
    public void DeleteCourse(Integer id){
        this.Crud.deleteById(id);
    }




}
