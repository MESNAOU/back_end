package com.example.demo.operations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudCourses extends JpaRepository<Courses,Integer> {

    public List<Courses> findByTopicId(Integer id);

    public  List<Courses> findByTopicTitle(String name) ;
}
