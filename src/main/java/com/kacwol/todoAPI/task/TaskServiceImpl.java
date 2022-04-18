package com.kacwol.todoAPI.task;

import com.kacwol.todoAPI.category.Category;
import com.kacwol.todoAPI.category.CategoryServiceImpl;
import com.kacwol.todoAPI.data.Status;
import com.kacwol.todoAPI.user.User;
import com.kacwol.todoAPI.user.UserNotFoundException;
import com.kacwol.todoAPI.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    private final CategoryServiceImpl categoryService;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, UserRepo userRepo, CategoryServiceImpl categoryService) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.categoryService = categoryService;
    }

    @Override
    public Task getTask(Long taskId, Long userId) {

        return taskRepo.findByIdAndUserId(taskId, userId).orElseThrow(() -> {
            throw new TaskNotFoundException();
        });
    }

    @Override
    public List<Task> getAllTasks(Long userId){

        return taskRepo.findAllByUserId(userId);
    }

    @Override
    public void addTask(TaskDto taskDto, Long categoryId, Long userId) {

        Category category = categoryService.getCategory(categoryId,userId);
        User user = userRepo.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Task task = new Task(taskDto, category, user);
        taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        taskRepo.deleteByIdAndUserId(taskId,userId);
    }

    @Override
    public void changeTitle(Long taskId, String newTitle, Long userId) {

        Task task = getTask(taskId,userId);
        task.changeTitle(newTitle);
        taskRepo.save(task);
    }

    @Override
    public void changeDescription(Long taskId, String newDescription, Long userId) {

        Task task = getTask(taskId,userId);
        task.changeDescription(newDescription);
        taskRepo.save(task);
    }

    @Override
    public void changeDueDate(Long taskId, LocalDateTime newDueDate, Long userId) {

        Task task = getTask(taskId,userId);
        task.changeDueDate(newDueDate);
        taskRepo.save(task);
    }

    @Override
    public void changeStatus(Long taskId, Status newStatus, Long userId) {

        Task task = getTask(taskId,userId);
        task.changeStatus(newStatus);
        taskRepo.save(task);
    }

    @Override
    public void changeCategory(Long taskId, Long categoryId, Long userId) {

        Task task = getTask(taskId,userId);
        Category category = categoryService.getCategory(categoryId,userId);
        task.changeCategory(category);
        taskRepo.save(task);
    }
}
