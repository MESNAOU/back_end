package com.example.demo.mappers;

import com.example.demo.dto.ResponsableDTO;
import com.example.demo.entities.Responsable;

import org.springframework.stereotype.Service;
@Service

public class ResponsableMapper {

        public ResponsableDTO fromResponsable(Responsable reponsable){
            ResponsableDTO medecinResponseDTO=new ResponsableDTO(reponsable);
            return medecinResponseDTO;
        }
}
