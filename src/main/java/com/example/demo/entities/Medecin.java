package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

public class Medecin extends Responsable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cin;

    @Column(unique = true)
    private String inpe;

    @Column(unique = true)
    private String ppr;

    private boolean estMedcinESJ;

    private boolean estGeneraliste;

    private String specialite;

    private boolean confirmed =false;

    private boolean isFirstAuth=true;

    @Column(columnDefinition = "TEXT")
    private String aProposDeMoi;

    @ElementCollection
    @CollectionTable(name = "langues_parlees", joinColumns = @JoinColumn(name = "medecin_id"))
    @Column(name = "langue")
    private List<String> languesParlees;
    
    @ElementCollection
    @CollectionTable(name = "specialites_medicales", joinColumns = @JoinColumn(name = "medecin_id"))
    @Column(name = "specialite")
    private List<String> specialites;

    @Column(name = "evaluation", columnDefinition = "INTEGER DEFAULT 0")
    private Integer evaluation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isEstMedcinESJ() {
		return estMedcinESJ;
	}

	public void setEstMedcinESJ(boolean estMedcinESJ) {
		this.estMedcinESJ = estMedcinESJ;
	}

	public boolean isEstGeneraliste() {
		return estGeneraliste;
	}

	public void setEstGeneraliste(boolean estGeneraliste) {
		this.estGeneraliste = estGeneraliste;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public boolean isFirstAuth() {
		return isFirstAuth;
	}

	public void setFirstAuth(boolean isFirstAuth) {
		this.isFirstAuth = isFirstAuth;
	}

	public String getaProposDeMoi() {
		return aProposDeMoi;
	}

	public void setaProposDeMoi(String aProposDeMoi) {
		this.aProposDeMoi = aProposDeMoi;
	}

	public List<String> getLanguesParlees() {
		return languesParlees;
	}

	public void setLanguesParlees(List<String> languesParlees) {
		this.languesParlees = languesParlees;
	}

	public List<String> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<String> specialites) {
		this.specialites = specialites;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	@Override
    public String isRole() {
    	return this.specialite;
    }

	
}
