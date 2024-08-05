package ma.inpt.esj.services;



import java.util.List;
import java.util.Map;

import ma.inpt.esj.Exception.ProfessionnelException;
import ma.inpt.esj.Exception.ProfessionnelNotFoundException;
import ma.inpt.esj.dto.ProfessionnelSanteDTO;
import ma.inpt.esj.entities.ProfessionnelSante;

public interface ProfessionnelService {
    ProfessionnelSanteDTO saveProfessionnel(ProfessionnelSante professionnel) throws ProfessionnelException;

    ProfessionnelSanteDTO getProfessionnelById(Long id) throws ProfessionnelNotFoundException;

    ProfessionnelSanteDTO updateProfessionnel(Long id, Map<String, Object> updates) throws ProfessionnelNotFoundException;

    void deleteProfessionnel(Long id) throws ProfessionnelNotFoundException, ProfessionnelException;
    List<ProfessionnelSanteDTO> getAllProfessionnels();

}
