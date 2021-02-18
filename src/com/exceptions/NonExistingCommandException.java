package com.exceptions;

public class NonExistingCommandException extends Exception {
    public NonExistingCommandException(String message){
        super(message);
    }
}
