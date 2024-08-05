package ma.inpt.esj.services;

import java.util.List;
import java.util.Map;

import ma.inpt.esj.Exception.MedecinException;
import ma.inpt.esj.Exception.MedecinNotFoundException;
import ma.inpt.esj.dto.MedecinResponseDTO;
import ma.inpt.esj.entities.Medecin;

public interface MedecinService extends ConfirmeMailService<Medecin> {
    MedecinResponseDTO saveMedecin(Medecin medecin) throws MedecinException;

    MedecinResponseDTO getMedecinById(Long id) throws MedecinNotFoundException;

    MedecinResponseDTO updateMedecinPartial(Long id, Map<String, Object> updates) throws MedecinNotFoundException;

    void deleteMedecin(Long id) throws MedecinNotFoundException, MedecinException;
        List<MedecinResponseDTO> getAllMedecins();

}
