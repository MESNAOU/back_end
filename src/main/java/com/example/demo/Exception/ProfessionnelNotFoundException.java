package com.example.demo.Exception;

public class ProfessionnelNotFoundException extends Exception{
    public ProfessionnelNotFoundException(String message) {
        super(message);
    }

    public ProfessionnelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
