package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.Exception.MedecinException;
import com.example.demo.Exception.MedecinNotFoundException;
import com.example.demo.dto.MedecinResponseDTO;
import com.example.demo.entities.Medecin;

public interface MedecinService extends ConfirmeMailService<Medecin> {
    MedecinResponseDTO saveMedecin(Medecin medecin) throws MedecinException;

    MedecinResponseDTO getMedecinById(Long id) throws MedecinNotFoundException;

    MedecinResponseDTO updateMedecinPartial(Long id, Map<String, Object> updates) throws MedecinNotFoundException;

    void deleteMedecin(Long id) throws MedecinNotFoundException, MedecinException;
        List<MedecinResponseDTO> getAllMedecins();

}
