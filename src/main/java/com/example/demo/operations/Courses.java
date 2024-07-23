package com.example.demo.operations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Courses {
    @Id
    @Column(name="Course_id",unique = true,nullable = false)
    public Integer id;

    @Column(name="Description",columnDefinition = "TEXT",nullable = false)
    public String descprition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Id")
    @JsonIgnore
    Topics topic;

    public Courses(Integer id,String descprition){
        this.id=id;
        this.descprition=descprition;
    }
    public Courses(){

    }
    public Integer getId(){
        return this.id;
    }

    public String getDescprition() {
        return descprition;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setDescprition(String descprition) {
        this.descprition = descprition;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }
}
