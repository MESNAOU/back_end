package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity

public  class InfoUser {
    @Id
    /*
    @Column(name="id_user",columnDefinition = "INTEGER",nullable = false)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column(name="nom",columnDefinition = "TEXT",nullable = false)
    private String nom;

    //@Column(name="prenom",columnDefinition = "TEXT",nullable = false)
    private String prenom;

    //@Column(name="telephone",columnDefinition = "TEXT",nullable = false)
    private String numTel;
    
   // @Column(name="mail",columnDefinition = "TEXT",nullable = false)
    @Column(unique = true)
    private String mail;

    private String motDePasse;

    public InfoUser(Long id, String nom, String prenom, String numTel, String mail, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.mail = mail;
    }

    public InfoUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
    
    
}

