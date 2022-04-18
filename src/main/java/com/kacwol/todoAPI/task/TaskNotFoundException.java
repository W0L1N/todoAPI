package com.kacwol.todoAPI.task;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException() {
        super("Task not found.");
    }
}
