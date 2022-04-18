package com.kacwol.todoAPI.task;

import com.kacwol.todoAPI.data.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    Task getTask(Long taskId, Long userId);

    List<Task> getAllTasks(Long userId);

    void addTask(TaskDto taskDto, Long categoryId, Long userId);

    void deleteTask(Long taskId, Long userId);

    void changeTitle(Long taskId, String newTitle, Long userId);

    void changeDescription(Long taskId, String newDescription, Long userId);

    void changeDueDate(Long taskId, LocalDateTime newDueDate, Long userId);

    void changeStatus(Long taskId, Status status, Long userId);

    void changeCategory(Long taskId, Long CategoryId, Long UserId);
}
