package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entities.Administrateur;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class LiveForResponceDTO {
	private int id;

    private String subject;
    private LocalDateTime date;
    private String lienStreamYard;
    private String lienYoutube;
    private ThemeDTO thematique;
    private ResponsableDTO responsable;
    
    
	public LiveForResponceDTO(int id, String subject, LocalDateTime date, String lienStreamYard, String lienYoutube,
			ThemeDTO thematique, ResponsableDTO responsable) {
		super();
		this.id = id;
		this.subject = subject;
		this.date = date;
		this.lienStreamYard = lienStreamYard;
		this.lienYoutube = lienYoutube;
		this.thematique = thematique;
		this.responsable = responsable;
	}
	public LiveForResponceDTO() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public ThemeDTO getThematique() {
		return thematique;
	}
	public void setThematique(ThemeDTO thematique) {
		this.thematique = thematique;
	}
	public ResponsableDTO getResponsable() {
		return responsable;
	}
	public void setResponsable(ResponsableDTO responsable) {
		this.responsable = responsable;
	}
	@Override
	public String toString() {
		return "LiveDTOForCreation [id=" + id + ", subject=" + subject + ", date=" + date + ", lienStreamYard="
				+ lienStreamYard + ", lienYoutube=" + lienYoutube + ", thematique=" + thematique + ", responsable="
				+ responsable.getRole() + "]";
	}

    
    
}
