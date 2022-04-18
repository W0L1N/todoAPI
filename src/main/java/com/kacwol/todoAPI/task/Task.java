package com.kacwol.todoAPI.task;

import com.kacwol.todoAPI.category.Category;
import com.kacwol.todoAPI.data.Status;
import com.kacwol.todoAPI.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private Status status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;


    public Task(TaskDto taskDto,Category category,User user) {
        this.title = taskDto.getTitle();
        this.description = taskDto.getDescription();
        this.dueDate = taskDto.getDueDate();
        this.category = category;
        this.user = user;
        status = Status.TO_DO;
    }

    public void changeTitle(String newTitle){
        this.title = newTitle;
    }
    public void changeDescription(String newDescription){
        this.description = newDescription;
    }
    public void changeDueDate(LocalDateTime newDueDate){
        this.dueDate = newDueDate;
    }
    public void changeStatus(Status newStatus){
        this.status = newStatus;
    }
    public void changeCategory(Category newCategory){
        this.category = newCategory;
    }
}
