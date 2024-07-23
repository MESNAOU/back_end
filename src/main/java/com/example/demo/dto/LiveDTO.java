package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class LiveDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String subject;
    private LocalDateTime date;
    private String lienStreamYard;
    private String lienYoutube;
    private AdministrateurDTO admin;
    private List<QuestionDTO> questions;
    private ThemeDTO thematique;
    private boolean active=false;

    @JsonProperty("responsable")
    private ResponsableDTO responsable;

   public LiveDTO(int id, String subject, LocalDateTime date, String lienStreamYard, String lienYoutube) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.lienStreamYard = lienStreamYard;
        this.lienYoutube = lienYoutube;
    }
    public LiveDTO(){

    }

    // Getters et setters
   public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getDate() {
        return date;
    }

        public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLienStreamYard() {
        return lienStreamYard;
    }

    public void setLienStreamYard(String lienStreamYard) {
        this.lienStreamYard = lienStreamYard;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }

    public ResponsableDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableDTO responsable) {
        this.responsable = responsable;
    }

    public AdministrateurDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdministrateurDTO admin) {
        this.admin = admin;
    }



    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public ThemeDTO getThematique() {
        return thematique;
    }

    public void setThematique(ThemeDTO thematique) {
        this.thematique = thematique;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
