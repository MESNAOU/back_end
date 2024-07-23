package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Responsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "responsable_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private InfoUser infoUser;
    @OneToMany(mappedBy = "responsable")
    @JsonIgnore
    private List<Live> lives;
    
    public abstract String isRole();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Live> getLives() {
		return lives;
	}
	public void setLives(List<Live> lives) {
		this.lives = lives;
	}
	public InfoUser getInfoUser() {
		return infoUser;
	}
	public void setInfoUser(InfoUser infoUser) {
		this.infoUser = infoUser;
	}
    
    
}
