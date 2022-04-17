package com.kacwol.todoAPI.category;

import com.kacwol.todoAPI.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @ManyToOne
    private User user;


    public Category(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Category(String title) {
        this.title = title;
    }

    public Category(CategoryDto categoryDto, User user){
        this.title = categoryDto.getTitle();
        this.user = user;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
