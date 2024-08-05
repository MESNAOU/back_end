package ma.inpt.esj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.inpt.esj.entities.ProfessionnelSante;
import ma.inpt.esj.repositories.ProfessionnelRepository;

@RestController
@RequestMapping("professionnels")
public class ProfessionelController {
    @Autowired
    ProfessionnelRepository professionnelRepository;
    
    @PostMapping
    public ResponseEntity<String> createOne(@RequestBody ProfessionnelSante Pr) {
        try {
        	System.out.println(Pr.toString());
        	this.professionnelRepository.save(Pr);
            return ResponseEntity.status(HttpStatus.CREATED).body("Professionnel created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Professionnel");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ProfessionnelSante>> getAll(){
		try {
			List<ProfessionnelSante> list_professionnels = this.professionnelRepository.findAll();
			if (list_professionnels.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(list_professionnels);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
}
