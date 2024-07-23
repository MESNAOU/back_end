package com.example.demo.dto;

public class QuestionDTO {
    private int id;
    private String contenu;

    public QuestionDTO(){

    }

    public QuestionDTO(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
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
}
