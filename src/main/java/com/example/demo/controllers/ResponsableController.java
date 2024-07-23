package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LiveDTO;
import com.example.demo.dto.ResponsableDTO;
import com.example.demo.entities.Administrateur;
import com.example.demo.entities.Responsable;
import com.example.demo.service.LiveService;
import com.example.demo.service.ResponsableService;

import jakarta.websocket.server.PathParam;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsables")
public class ResponsableController {
    @Autowired
    ResponsableService service;
    @Autowired
    LiveService liveService;

    /*@RequestMapping("/professionals")

    public List<DTORESPONSABLE> getAll(){
        return this.Server.getAll();
    }*/
    @GetMapping
    public ResponseEntity<List<ResponsableDTO>> getAlMedecins() {
        List<ResponsableDTO> medecins = this.service.getAll();
        return ResponseEntity.ok(medecins);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponsableDTO> getone(@PathVariable Long id){
        try {
        	ResponsableDTO responsable = this.service.getSingleOne(id);
            if (responsable != null) {
                return ResponseEntity.status(HttpStatus.OK).body(responsable);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    // Lives animated by a responsable
    @GetMapping("/{id}/streams")
    public ResponseEntity<List<LiveDTO>> getAnimatedLives(@PathVariable("id") Long id, @PathParam(value = "phase") String phase){
    	try {
    		List<LiveDTO> L;
    		if (phase.equals("goingto")) L = this.liveService.getongoingToan(id);
    		else L = this.liveService.getAllAnimated(id);
    		if (L.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			return ResponseEntity.status(HttpStatus.OK).body(L);
    	} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    	
    }
}
