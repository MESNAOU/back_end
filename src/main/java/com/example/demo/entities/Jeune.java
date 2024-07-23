package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

public class Jeune {
    @Id
    //@SequenceGenerator(name="generated",sequenceName ="generated",allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generated")
    //@Column(name = "id_Jeune")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sexe;
    private String dateNaissance;
    private int age;
    private int identifiantPatient;
    private boolean scolarise;
    private String cin;

    /*@OneToMany(mappedBy = "jeune")
    List<Questions> questions;*/
   /* @ManyToOne
    @JoinColumn(name="id_thematique")
    @JsonIgnore
    PropositionThématique Thematique_Choisit;*/
    @OneToOne(cascade = CascadeType.ALL)
    private InfoUser infoUser;
    public boolean getScolarise() {
        return scolarise;
    }




    /*public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }*/
}
