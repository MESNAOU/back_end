package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MedecinResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String cin;
    private String inpe;
    private String ppr;
    private Boolean estMedcinESJ;
    private Boolean estGeneraliste;
    private String specialite;

    public MedecinResponseDTO(String s) {
    }
    
    public MedecinResponseDTO() {
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getInpe() {
		return inpe;
	}

	public void setInpe(String inpe) {
		this.inpe = inpe;
	}

	public String getPpr() {
		return ppr;
	}

	public void setPpr(String ppr) {
		this.ppr = ppr;
	}

	public Boolean getEstMedcinESJ() {
		return estMedcinESJ;
	}

	public void setEstMedcinESJ(Boolean estMedcinESJ) {
		this.estMedcinESJ = estMedcinESJ;
	}

	public Boolean getEstGeneraliste() {
		return estGeneraliste;
	}

	public void setEstGeneraliste(Boolean estGeneraliste) {
		this.estGeneraliste = estGeneraliste;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
    
    
}
