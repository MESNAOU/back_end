package com.example.demo.mappers;


import org.springframework.stereotype.Service;

import com.example.demo.dto.ProfessionnelSanteDTO;
import com.example.demo.entities.ProfessionnelSante;

@Service
public class ProfessionnelMapper {
    public ProfessionnelSanteDTO fromProfessionnel(ProfessionnelSante professionnel){
        ProfessionnelSanteDTO professionnelSanteDTO=new ProfessionnelSanteDTO();
        professionnelSanteDTO.setId(professionnel.getId());
        professionnelSanteDTO.setNom(professionnel.getInfoUser().getNom());
        professionnelSanteDTO.setPrenom(professionnel.getInfoUser().getPrenom());
        professionnelSanteDTO.setMail(professionnel.getInfoUser().getMail());
        professionnelSanteDTO.setNumTel(professionnel.getInfoUser().getNumTel());
        professionnelSanteDTO.setCin(professionnel.getCin());
        professionnelSanteDTO.setInpe(professionnel.getInpe());

        return professionnelSanteDTO;
    }
}
