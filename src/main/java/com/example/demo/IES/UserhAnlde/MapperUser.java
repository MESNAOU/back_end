package com.example.demo.IES.UserhAnlde;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponsableDTO;
import com.example.demo.entities.Responsable;
import com.example.demo.repositories.ResponsableRepository;

@Service
public class MapperUser {
    @Autowired
    ResponsableRepository Medecin;
    public ResponsableDTO fromResponsable(Responsable medecin){
        ResponsableDTO responsable=new ResponsableDTO();
        responsable.setId(medecin.getId());
        responsable.setInfoUser(medecin.getInfoUser());
        responsable.setLives(medecin.getLives());
        if(this.Medecin.findById(medecin.getId()).isPresent()){
            responsable.setRole("MEDECIN");
        }
        else{
            responsable.setRole("INFIRMIER");
        }
        return responsable;
    }
}
