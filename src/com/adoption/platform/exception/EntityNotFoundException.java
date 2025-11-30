package com.adoption.platform.exception;

// Part of the 10 marks for Exception Handling
public class EntityNotFoundException extends Exception {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}