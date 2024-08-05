package ma.inpt.esj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import ma.inpt.esj.dto.LiveDTO;
import ma.inpt.esj.dto.LiveFeedbackDTO;
import ma.inpt.esj.entities.Jeune;
import ma.inpt.esj.services.JeuneService;
import ma.inpt.esj.services.LiveFeedbackService;
import ma.inpt.esj.services.QuestionService;

@RestController
@RequestMapping("/jeune")
public class JeuneController {
    @Autowired
    private JeuneService jeuneService;
    @Autowired
    QuestionService questionService;
    @Autowired
    LiveFeedbackService liveFeedbackService;

    @GetMapping
    public List<Jeune> getAllJeune() {
        return jeuneService.getAllJeunes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jeune> getJeuneById(@PathVariable Long id) {
        return jeuneService.getJeuneById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jeune createJeune(@RequestBody Jeune jeune) {
        return jeuneService.createJeune(jeune);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jeune> updateJeune(@PathVariable Long id, @RequestBody Jeune jeuneDetails) {
        return ResponseEntity.ok(jeuneService.updateJeune(id, jeuneDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJeune(@PathVariable Long id) {
        jeuneService.deleteJeune(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order-by-age-asc")
    public List<Jeune> getAllJeunesOrderByAgeAsc() {
        return jeuneService.getAllJeunesOrderByAgeAsc();
    }

    @GetMapping("/order-by-age-desc")
    public List<Jeune> getAllJeunesOrderByAgeDesc() {
        return jeuneService.getAllJeunesOrderByAgeDesc();
    }

    @GetMapping("/order-by-nom")
    public List<Jeune> getAllJeunesOrderByNom() {
        return jeuneService.getAllJeunesOrderByNom();
    }

    @GetMapping("/order-by-prenom")
    public List<Jeune> getAllJeunesOrderByPrenom() {
        return jeuneService.getAllJeunesOrderByPrenom();
    }

    @GetMapping("/get-by-sexe/{sexe}")
    public List<Jeune> getAllJeunesBySexe(@PathVariable String sexe) {
        return jeuneService.getAllJeunesBySexe(sexe);
    }

    @GetMapping("/get-by-nom/{nom}")
    public List<Jeune> getAllJeunesByNom(@PathVariable String nom) {
        return jeuneService.getAllJeunesByNom(nom);
    }
    /*
    @GetMapping("/{medecinId}/patients")
    public List<Jeune> getJeunesByMedecinId(@PathVariable Long medecinId) {
        return jeuneService.getJeunesByMedecinId(medecinId);
    }
    */

    @GetMapping("/{id}/streams")
    public ResponseEntity<List<LiveDTO>> getAllLives(@PathVariable int id, @PathParam(value = "phase") String phase, @PathParam(value = "limit") int limit){
		try {
			List<LiveDTO> L=null;
			if (phase.equals("question")) L = this.questionService.getonquestionsforuserId(id, limit);
			else if (phase.equals("final")) L = this.questionService.getonfinalforuserId(id, limit);
			if (L.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(L);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
    
    @PostMapping("/{jeuneId}/streams/{liveId}/feedbacks")
    public ResponseEntity<String> createLive(@RequestBody LiveFeedbackDTO liveFeedback, @PathVariable("liveId") int liveId, @PathVariable("jeuneId") Long jeuneId) {
    	try {
        	this.liveFeedbackService.createFeedback(liveFeedback, liveId, jeuneId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Live feedback created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Live feedback: "+e.getMessage());
        }
    }
    @GetMapping("/{jeuneId}/streams/last")
    public ResponseEntity<LiveDTO> getSingleLive(@PathVariable int jeuneId) {
    	try {
    		LiveDTO live = this.liveFeedbackService.getLastLiveUnanswered(jeuneId);
            if (live != null) {
                return ResponseEntity.status(HttpStatus.OK).body(live);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
