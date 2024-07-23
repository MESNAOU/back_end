package com.example.demo.Exception;

public class ProfessionnelException extends Exception{
    public ProfessionnelException(String message) {
        super(message);
    }

    public ProfessionnelException(String message, Throwable cause) {
        super(message, cause);
    }
}
