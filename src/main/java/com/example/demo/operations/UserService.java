package com.example.demo.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserCruder Cruder;

    public List<User> getAllUser(){
        return  this.Cruder.findAll();
    }
    public Optional<User> getSingleUser(String id){
        return this.Cruder.findByPassword(id);
    }
    public void CreateUser(User U){
        this.Cruder.save(U);
    }
    public void UpdateUser(User U){
        this.Cruder.save(U);
    }
    public void DeleteUser(Integer U){
        this.Cruder.deleteById(U);
    }


}
