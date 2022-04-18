package com.kacwol.todoAPI.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TaskDto {

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private Long categoryId;
}
