package com.example.demo.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Live {
    @Id
    @SequenceGenerator(name="generated",sequenceName ="generated",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generated")
    @Column(name = "id_Live")
    private int id;

    @Column(name = "Sujet",columnDefinition = "TEXT",nullable = false)
    private String subject;
    @Column(name = "Date",columnDefinition = "TEXT",nullable = false)
    private LocalDateTime date;
    @Column(name = "StreamYard",columnDefinition = "TEXT",nullable = false)
    private String lienStreamYard;
    @Column(name = "Lien_Youtube",columnDefinition = "TEXT",nullable = false)

    private String lienYoutube;
    
    @Lob
    @Column(name = "Image_Data")
    private byte[] imageData;
    
    private boolean active=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="responsable_id")
    private Responsable responsable;

   /* @JoinColumn(name="id_Responsable")

    @JsonProperty("responsable")

    private Responsable responsable;*/

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_admin")

    private Administrateur admin;

    @OneToMany(mappedBy = "live")
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "id_thematique")
    @JsonProperty("thématique")
    private Theme thematique;

    @OneToMany(mappedBy = "live")
    private List<LiveFeedback> feedbacks;

    public Live(){

    }
    public Live(int id,String subject,LocalDateTime date,String l1,String l2){
        this.id=id;
       this.subject=subject;
        this.date=date;
        this.lienStreamYard=l1;
        this.lienYoutube=l2;

    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLienStreamYard() {
        return lienStreamYard;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setId(int id) {
        this.id = id;
    }

   public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setLienStreamYard(String lienStreamYard) {
        this.lienStreamYard = lienStreamYard;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Administrateur getAdmin() {
        return admin;
    }

    public void setAdmin(Administrateur admin) {
        this.admin = admin;
    }

    public List<Question> getQuestionsList() {
        return questions;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questions = questionsList;
    }

    public Theme getThematiques() {
        return thematique;
    }

    public void setThematiques(Theme thematiques) {
        this.thematique = thematiques;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public List<LiveFeedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<LiveFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
}
