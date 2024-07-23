package com.example.demo.IES.UserhAnlde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponsableDTO;
import com.example.demo.entities.Responsable;
import com.example.demo.repositories.ResponsableRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class ControllerUser {
    @Autowired
    ResponsableRepository R;
    @Autowired
    MapperUser Mapper;


    @RequestMapping("/getall")
    public List<ResponsableDTO> getAllu(){
        List<ResponsableDTO> L=new ArrayList<>();
        List<Responsable> L2=this.R.findAll();
        for(int i=0;i<L2.size();i++){
           L.add(this.Mapper.fromResponsable(L2.get(i)));
        }
        return L;
    }
}
