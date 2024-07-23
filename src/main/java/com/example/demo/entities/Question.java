package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @SequenceGenerator(name="generated",sequenceName ="generated",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generated")
    @Column(name = "id_question")
    private  int id;
    @Column(name="Contenu",columnDefinition = "TEXT",nullable = false)
    String contenu;

    /*@ManyToOne
    @JoinColumn(name="id_Jeune")
    private Jeunes jeune;*/

    @ManyToOne
    @JoinColumn(name="id_Live")
    @JsonIgnore
    private  Live live;
    public Question(){

    }

    public Question(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /*public Jeunes getJeune() {
        return jeune;
    }

    public void setJeune(Jeunes jeune) {
        this.jeune = jeune;
    }*/

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }
}
