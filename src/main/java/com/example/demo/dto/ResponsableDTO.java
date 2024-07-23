package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.InfoUser;
import com.example.demo.entities.Live;
import com.example.demo.entities.Responsable;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeName("Responsable")


public class ResponsableDTO {
	private Long id;
    private InfoUser infoUser;
    private List<Live> lives;
    private String role;
    
    public ResponsableDTO() {
		super();
	}
    
    public ResponsableDTO(Responsable responsable) {
		super();
		this.id = responsable.getId();
		this.infoUser = responsable.getInfoUser();
		this.lives = responsable.getLives();
		this.role = responsable.isRole();
	}

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
		return lives;
	}

	public void setLives(List<Live> lives) {
		this.lives = lives;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    

    
}

