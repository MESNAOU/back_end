package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.Jeune;

public class ThemeDTO {
    private int id;
    private String contenu;
    //private List<Jeunes> Jeunes;

    public ThemeDTO(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }
    public ThemeDTO (){

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

    /*public List<com.example.demo.IES.Jeunes> getJeunes() {
        return Jeunes;
    }

    public void setJeunes(List<com.example.demo.IES.Jeunes> jeunes) {
        Jeunes = jeunes;
    }*/
}
