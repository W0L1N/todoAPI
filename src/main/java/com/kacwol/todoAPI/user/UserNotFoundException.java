package com.kacwol.todoAPI.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found");
    }
}
