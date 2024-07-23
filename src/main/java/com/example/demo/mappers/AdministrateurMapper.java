package com.example.demo.mappers;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AdministrateurDTO;
import com.example.demo.entities.Administrateur;

@Service
public class AdministrateurMapper {
    public AdministrateurDTO adminToAdminDTO(Administrateur admin){
        AdministrateurDTO adminResponseDTO=new AdministrateurDTO();
        adminResponseDTO.setId(admin.getId());
        adminResponseDTO.setInfoUser(admin.getInfoUser());
        return adminResponseDTO;
    }
}
