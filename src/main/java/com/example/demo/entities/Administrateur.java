package com.example.demo.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder

/*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Administrateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_user_id")
    private InfoUser infoUser;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)

    @JsonIgnore
    List<Live> Lives;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public InfoUser getInfoUser() {
		return infoUser;
	}
	public void setInfoUser(InfoUser infoUser) {
		this.infoUser = infoUser;
	}
	
	public List<Live> getLives() {
		return Lives;
	}
	public void setLives(List<Live> lives) {
		Lives = lives;
	}


    // Constructeur avec List<Live>
    
    

}
