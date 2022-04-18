package com.kacwol.todoAPI.task;

import com.kacwol.todoAPI.category.Category;
import com.kacwol.todoAPI.category.CategoryServiceImpl;
import com.kacwol.todoAPI.data.Status;
import com.kacwol.todoAPI.user.User;
import com.kacwol.todoAPI.user.UserRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private CategoryServiceImpl categoryService;
    @InjectMocks
    private TaskServiceImpl taskService;

    Long taskId;
    Long userId;
    Long categoryId;
    String title;
    String description;
    LocalDateTime localDateTime;
    Status status;
    String username;
    User user;
    String categoryTitle;
    Category category;
    TaskDto taskDto;
    Task task;

    @Before
    public void start(){
        taskId =1L;
        userId =1L;
        categoryId =1L;
        title = "taskname";
        description = "taskdesc";
        localDateTime = LocalDateTime.now();
        status = Status.TO_DO;
        username = "username";
        user = new User(userId,username);
        categoryTitle = "categoryname";
        category = new Category(categoryId,categoryTitle,user);
        taskDto = new TaskDto(title,description,localDateTime,categoryId);
        task = new Task(taskId,title,description,localDateTime, Status.TO_DO,user,category);
    }

    @Test
    public void addTask_shouldCreateTask(){
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(categoryService.getCategory(categoryId,1L)).thenReturn(category);

        taskService.addTask(taskDto,categoryId,1L);
        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(title,actual.getTitle());
        Assert.assertEquals(description,actual.getDescription());
        Assert.assertEquals(localDateTime,actual.getDueDate());
        Assert.assertEquals(category,actual.getCategory());
        Assert.assertEquals(status,actual.getStatus());
        Assert.assertEquals(user,actual.getUser());
    }

    @Test
    public void changeDescription_shouldChangeDescription(){
        String newDesription = "newtaskdesc";
        when(taskRepo.findByIdAndUserId(taskId,userId)).thenReturn(Optional.of(task));

        taskService.changeDescription(taskId,newDesription,userId);
        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(newDesription,actual.getDescription());
    }

    @Test
    public void changeTitle_shouldChangeTitle(){
        String newTitle = "newTaskTitle";
        when(taskRepo.findByIdAndUserId(taskId,userId)).thenReturn(Optional.of(task));

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        taskService.changeTitle(taskId,newTitle,userId);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(newTitle,actual.getTitle());
    }

    @Test
    public void changeDueDate_shouldChangeDueDate(){
        LocalDateTime newDueDate = LocalDateTime.of(1900,12,24,13,50);
        when(taskRepo.findByIdAndUserId(taskId,userId)).thenReturn(Optional.of(task));

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        taskService.changeDueDate(taskId,newDueDate,userId);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(newDueDate,actual.getDueDate());
    }

    @Test
    public void changeStatus_shouldChangeStatus(){
        Status newStatus =Status.IN_PROGRESS;
        when(taskRepo.findByIdAndUserId(taskId,userId)).thenReturn(Optional.of(task));

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        taskService.changeStatus(taskId,newStatus,userId);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(newStatus,actual.getStatus());
    }

    @Test
    public void changeCategory_shouldChangeCategory(){
        Category newCategory = new Category("newCategory");
        when(taskRepo.findByIdAndUserId(taskId,userId)).thenReturn(Optional.of(task));
        when(categoryService.getCategory(2L,userId)).thenReturn(newCategory);

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        taskService.changeCategory(taskId,2L,userId);
        verify(taskRepo).save(argumentCaptor.capture());
        Task actual = argumentCaptor.getValue();

        Assert.assertEquals(newCategory,actual.getCategory());
    }
}
