package com.example.demo.operations;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="Topicss")
public class Topics {
    @Id
    @SequenceGenerator(name="Topics",
            sequenceName = "Topics",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "Topics"
    )


    @Column(name="Id",
            updatable = false
    )
    public Integer id;
    @Column(name="NAME_OF_THE_TOPIC",
         columnDefinition="TEXT",
            nullable = false
    )
    public String title;
    public Topics(int id,String title){
        this.id=id;
        this.title=title;
    }
    public Topics(){

    }

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Courses> L;
    public  void setId(int i){
        this.id=i;
    }
    public  void setTitle(String i){
        this.title=i;
    }
    public Integer getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }

    public List<Courses> getL() {
        return L;
    }

    public void setL(List<Courses> l) {
        L = l;
    }
}
