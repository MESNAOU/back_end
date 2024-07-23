package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Theme {
    @Id
    @SequenceGenerator(name="generated",sequenceName ="generated",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generated")
    @Column(name = "id_thematique")
    private int id;
    @Column(name="thematique",columnDefinition = "TEXT",nullable = false)
    String contenu;

   /* @OneToMany(mappedBy = "Thematique_Choisit")
    List<Jeunes> Jeunes;*/

    //@ManyToOne(fetch = FetchType.LAZY)


    //@JoinColumn(name = "id_Live")
    @OneToMany(mappedBy="thematique")
    List<Live> lives;
    public  Theme(){

    }

    public Theme(int id, String contenu) {
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

   /* public List<Jeunes> getJeunes() {
        return Jeunes;
    }

    public void setJeunes(List<Jeunes> jeunes) {
        Jeunes = jeunes;
    }*/

    public List<Live> getLive() {
        return lives;
    }

    public void setLive(List<Live> live) {
        this.lives = live;
    }
    /* public List<Live> getLives() {
        return Lives;
    }

    public void setLives(List<Live> lives) {
        Lives = lives;
    }*/
}
