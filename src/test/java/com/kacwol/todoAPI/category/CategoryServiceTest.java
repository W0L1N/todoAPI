package com.kacwol.todoAPI.category;

import com.kacwol.todoAPI.user.User;
import com.kacwol.todoAPI.user.UserNotFoundException;
import com.kacwol.todoAPI.user.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void addCategory_shouldCreateCategory(){
        CategoryDto categoryDto =  new CategoryDto("ez");
        User user = new User(1L,"XD");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        categoryService.addCategory(categoryDto,1L);
        ArgumentCaptor<Category> argumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepo).save(argumentCaptor.capture());
        Category actual = argumentCaptor.getValue();


        Assert.assertEquals(user,actual.getUser());
        Assert.assertEquals(categoryDto.getTitle(),actual.getTitle());
    }

    @Test
    public void addCategory_shouldThrowUserNotFoundException(){
        CategoryDto categoryDto =  new CategoryDto("ez?");

        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        Assert.assertThrows(UserNotFoundException.class,()->categoryService.addCategory(categoryDto,1L));
    }

    @Test
    public void changeCategoryTitle_shouldChangeCategoryTitle(){
        Long categoryId = 1L;
        String title = "ez?";
        String newTitle = "notEz";
        Category category = new Category(title);

        when(categoryRepo.findByIdAndUserId(categoryId,1L)).thenReturn(Optional.of(category));
        categoryService.changeCategoryTitle(categoryId,newTitle,1L);
        ArgumentCaptor<Category> argumentCaptor =  ArgumentCaptor.forClass(Category.class);
        verify(categoryRepo).save(argumentCaptor.capture());
        Category actual = argumentCaptor.getValue();

        Assert.assertEquals(actual.getTitle(),newTitle);
    }
}
