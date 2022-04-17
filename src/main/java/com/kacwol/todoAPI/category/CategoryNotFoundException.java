package com.kacwol.todoAPI.category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Category not found.");
    }
}
