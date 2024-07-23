package com.example.demo.dto;

import com.example.demo.entities.InfoUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class AdministrateurDTO {
    private Long id;
    private InfoUser infoUser;
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
	public AdministrateurDTO(Long id, InfoUser infoUser) {
		super();
		this.id = id;
		this.infoUser = infoUser;
	}
    
	public AdministrateurDTO() {
		super();
	}
}