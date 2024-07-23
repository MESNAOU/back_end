package com.example.demo.service;


import com.example.demo.dto.ResponsableDTO;
import com.example.demo.entities.Responsable;
import com.example.demo.mappers.ResponsableMapper;
import com.example.demo.repositories.ResponsableRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsableService {
    @Autowired
    ResponsableRepository responsableRepository;
    @Autowired
    ResponsableMapper Mapper;
    
    public List<ResponsableDTO> getAll(){

        List<Responsable> L=this.responsableRepository.findAll();
        List<ResponsableDTO> L2=new ArrayList<>();
        for(int i=0;i<L.size();i++){
            L2.add(this.Mapper.fromResponsable(L.get(i)));
        }
        return  L2;
    }
    public ResponsableDTO getSingleOne(Long id){

        return this.Mapper.fromResponsable(this.responsableRepository.findById(id).get());
    }
    public void createOne(Responsable R){

        this.responsableRepository.save(R);
    }
    public void updateOne(Responsable R){
        this.responsableRepository.save(R);
    }

    public void deleteOne(Long id){
        this.responsableRepository.deleteById(id);
    }
}
