package com.example.demo.controllers;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.LiveNotFoundException;
import com.example.demo.dto.LiveDTO;
import com.example.demo.dto.LiveFeedbackDTO;
import com.example.demo.dto.LiveForCreationDTO;
import com.example.demo.entities.LiveFeedback;
import com.example.demo.enums.LiveEvaluation;
import com.example.demo.service.LiveFeedbackService;
import com.example.demo.service.LiveService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/streams")
public class LiveController {
    @Autowired
    LiveService service;
    @Autowired
    LiveFeedbackService liveFeedbackService;

    @GetMapping
    public ResponseEntity<List<LiveDTO>> getAllLives(@PathParam(value = "phase") String phase){
		try {
			List<LiveDTO> L;
			if (phase.equals("outdated")) L = this.service.getPassedLives();
			else if (phase.equals("question")) L = this.service.getonquestionsforuser();
			else if (phase.equals("final")) L = this.service.getonfinalforuser();
			else L = this.service.getAllLives();
			if (L.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(L);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
    
    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<LiveDTO> getSingleLive(@PathVariable int id) {
    	try {
    		LiveDTO live = this.service.getSingleLive(id);
            if (live != null) {
                return ResponseEntity.status(HttpStatus.OK).body(live);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/last")
    public ResponseEntity<LiveDTO> getSingleLive() {
    	try {
    		LiveDTO live = this.service.getLastLive();
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
    
    //to activate
    @PatchMapping("/{idlive}")
     public ResponseEntity<String> activate(@PathVariable("idlive") int id){
        try {
        	this.service.activatdes(id);
            return ResponseEntity.status(HttpStatus.OK).body("Le live d'id "+id+" est désormais activé.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
        }
    }
    
        // deleting
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLive ( @PathVariable("id") int id){
            try {
            	this.service.deleteLive(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } catch (LiveNotFoundException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        
        
        
        ////////////////////////////Feedbacks
        @GetMapping("/{liveId}/opinions")
        public ResponseEntity<List<String>> getAllLiveFeedbackOpinions(@PathVariable int liveId){
    		try {
    			List<String> l = this.liveFeedbackService.getOpinions(liveId);
    			if (l.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    			return ResponseEntity.status(HttpStatus.OK).body(l);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		}
        }
        
        @GetMapping("/{liveId}/suggestedThemes")
        public ResponseEntity<List<String>> getAllLiveFeedbackThemes(@PathVariable int liveId){
    		try {
    			List<String> l = this.liveFeedbackService.getSuggestedThemes(liveId);
    			if (l.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    			return ResponseEntity.status(HttpStatus.OK).body(l);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		}
        }
        
        @GetMapping("/{liveId}/evaluations")
        public ResponseEntity<Map<LiveEvaluation, Integer>> getAllLiveFeedbackEvaluations(@PathVariable int liveId){
    		try {
    			Map<LiveEvaluation, Integer> l = this.liveFeedbackService.getEvaluation(liveId);
    			return ResponseEntity.status(HttpStatus.OK).body(l);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		}
        }
        
        @GetMapping("/{liveId}/recommendations")
        public ResponseEntity<Map<Boolean, Integer>> getAllLiveFeedbackrecommended(@PathVariable int liveId){
    		try {
    			Map<Boolean, Integer> l = this.liveFeedbackService.getRecommended(liveId);
    			return ResponseEntity.status(HttpStatus.OK).body(l);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		}
        }
        
        @PostMapping("/{liveId}/feedbacks")

        public  ResponseEntity<String>  createLive(@RequestBody LiveFeedbackDTO liveFeedback, @PathVariable("liveId") int id) {
        	try {
            	this.liveFeedbackService.createFeedback(liveFeedback, id);
                return ResponseEntity.status(HttpStatus.CREATED).body("Live feedback created successfully");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Live feedback");
            }
        }
}


