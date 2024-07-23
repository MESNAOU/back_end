package com.example.demo.operations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    CrudOPERATIONS Cruder;
    public List<Topics> getAllTopics(){

        List<Topics> L=new ArrayList<Topics>();
        Iterator E=this.Cruder.findAll().iterator();
        while(E.hasNext()){
            L.add((Topics) E.next());
        }





        return L;
    }
    public Optional<Topics> geSingleTopics(Integer i){
        return this.Cruder.findById(i);
    }
    public void CreateTopic(Topics T){
        this.Cruder.save(T);
    }
    public void UpdateTopics(Topics T,Integer id){
        this.Cruder.save(T);
    }
    public void DeleteTopic(Integer T){
        this.Cruder.deleteById(T);
    }
    @Transactional
    public void DeleteTopicName(String n){this.Cruder.deleteByTitle(n);}



}
