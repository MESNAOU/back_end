package com.example.demo.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService Service;


    @CrossOrigin(origins = "http://localhost:5173")

    @RequestMapping("/Users")
    public List<User> getAllUsers(){
        return  this.Service.getAllUser();
    }
    @RequestMapping("/Users/{password}")
    public Optional<User> getSingleUserById(@PathVariable("password") String id){
        return  this.Service.getSingleUser(id);
    }

    @PostMapping("/Users")
    public void CreateOne(@RequestBody User U){
        this.Service.CreateUser(U);
    }

    @PutMapping("/Users/{id}")
    public void UpdateOne(@RequestBody User U){
        this.Service.CreateUser(U);
    }



    @DeleteMapping("/Users/{id}")
    public void DeleteOne(@PathVariable Integer id){
        this.Service.DeleteUser(id);
    }



}
