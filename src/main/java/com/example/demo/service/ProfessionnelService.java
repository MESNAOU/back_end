package com.example.demo.service;



import java.util.List;
import java.util.Map;

import com.example.demo.Exception.ProfessionnelException;
import com.example.demo.Exception.ProfessionnelNotFoundException;
import com.example.demo.dto.ProfessionnelSanteDTO;
import com.example.demo.entities.ProfessionnelSante;

public interface ProfessionnelService {
    ProfessionnelSanteDTO saveProfessionnel(ProfessionnelSante professionnel) throws ProfessionnelException;

    ProfessionnelSanteDTO getProfessionnelById(Long id) throws ProfessionnelNotFoundException;

    ProfessionnelSanteDTO updateProfessionnel(Long id, Map<String, Object> updates) throws ProfessionnelNotFoundException;

    void deleteProfessionnel(Long id) throws ProfessionnelNotFoundException, ProfessionnelException;
    List<ProfessionnelSanteDTO> getAllProfessionnels();

}
