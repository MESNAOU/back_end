package com.example.demo.operations;

import jakarta.persistence.*;

@Entity(name="users")
public class User {
    @Id
    @SequenceGenerator(name="sequencegen",sequenceName = "sequencegen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencegen")
    @Column(name="id_user",nullable = false,updatable = false)
    public Integer id;

    @Column(name="email",nullable = false,columnDefinition = "TEXT")
    public String email;

    @Column(name="password",nullable = false,columnDefinition = "TEXT",unique = true)
    public String password;

    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

     public User(){

     }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
