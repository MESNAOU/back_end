package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.ProfessionnelException;
import com.example.demo.Exception.ProfessionnelNotFoundException;
import com.example.demo.dto.ProfessionnelSanteDTO;
import com.example.demo.entities.ProfessionnelSante;
import com.example.demo.mappers.ProfessionnelMapper;
import com.example.demo.repositories.InfoUserRepository;
import com.example.demo.repositories.ProfessionnelRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProfessionnelServiceImpl implements ProfessionnelService {
	
	@Autowired 
	private ProfessionnelRepository professionnelRepository;
	@Autowired 
	private InfoUserRepository userRepository;
	@Autowired 
	private ProfessionnelMapper professionnelMapper;

    @Override
    public ProfessionnelSanteDTO saveProfessionnel(ProfessionnelSante professionnelSante) throws ProfessionnelException {
        if (professionnelRepository.existsByCin(professionnelSante.getCin())) {
            throw new ProfessionnelException("Le numéro de CIN spécifié est déjà utilisé par un autre utilisateur");
        }
        if (professionnelRepository.existsByInpe(professionnelSante.getInpe())) {
            throw new ProfessionnelException("Le numéro INPE spécifié est déjà utilisé par un autre utilisateur");
        }

        if (userRepository.existsByMail(professionnelSante.getInfoUser().getMail())) {
            throw new ProfessionnelException("L'email spécifié est déjà utilisé par un autre utilisateur");
        }

        ProfessionnelSante savedProfessionnelSante = professionnelRepository.save(professionnelSante);
        return professionnelMapper.fromProfessionnel(savedProfessionnelSante);
    }

    @Override
    public ProfessionnelSanteDTO getProfessionnelById(Long id) throws ProfessionnelNotFoundException {
        Optional<ProfessionnelSante> professionnelSanteOptional = professionnelRepository.findById(id);
        if (professionnelSanteOptional.isEmpty()) {
            throw new ProfessionnelNotFoundException("ProfessionnelSante non trouvé avec l'ID : " + id);
        }
        return professionnelMapper.fromProfessionnel(professionnelSanteOptional.get());
    }

    @Override
    public ProfessionnelSanteDTO updateProfessionnel(Long id, Map<String, Object> updates) throws ProfessionnelNotFoundException {
        ProfessionnelSante existingProfessionnelSante = professionnelRepository.findById(id)
                .orElseThrow(() -> new ProfessionnelNotFoundException("ProfessionnelSante not found with id " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingProfessionnelSante.getInfoUser().setNom((String) value);
                    break;
                case "prenom":
                    existingProfessionnelSante.getInfoUser().setPrenom((String) value);
                    break;
                case "mail":
                    existingProfessionnelSante.getInfoUser().setMail((String) value);
                    break;
                case "numTele":
                    existingProfessionnelSante.getInfoUser().setNumTel((String) value);
                    break;
                case "password":
                    existingProfessionnelSante.getInfoUser().setMotDePasse((String) value);
                    break;
                case "cin":
                    existingProfessionnelSante.setCin((String) value);
                    break;
                case "inpe":
                    existingProfessionnelSante.setInpe((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid attribute: " + key);
            }
        });

        userRepository.save(existingProfessionnelSante.getInfoUser());
        professionnelRepository.save(existingProfessionnelSante);

        return professionnelMapper.fromProfessionnel(existingProfessionnelSante);
    }

    @Override
    public void deleteProfessionnel(Long id) throws ProfessionnelNotFoundException, ProfessionnelException {
        Optional<ProfessionnelSante> professionnelSanteOptional = professionnelRepository.findById(id);
        if (professionnelSanteOptional.isPresent()) {
            try {
                professionnelRepository.delete(professionnelSanteOptional.get());
            } catch (Exception e) {
                throw new ProfessionnelException("Une erreur s'est produite lors de la suppression du médecin", e);
            }
        } else {
            throw new ProfessionnelNotFoundException("ProfessionnelSante non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public List<ProfessionnelSanteDTO> getAllProfessionnels() {
        List<ProfessionnelSante> professionnels = professionnelRepository.findAll();
        return professionnels.stream()
                .map(professionnelMapper::fromProfessionnel)
                .collect(Collectors.toList());
    }
}
