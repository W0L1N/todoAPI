package com.kacwol.todoAPI.category;

import com.kacwol.todoAPI.user.User;
import com.kacwol.todoAPI.user.UserNotFoundException;
import com.kacwol.todoAPI.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, UserRepo userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Category getCategory(Long categoryId, Long userId) {
        return categoryRepo.findByIdAndUserId(categoryId, userId).orElseThrow(() -> {
            throw new CategoryNotFoundException();
        });
    }

    @Override
    public void addCategory(CategoryDto category, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        categoryRepo.save(new Category(category, user));
    }

    @Override
    public void deleteCategory(Long categoryId, Long userId) {
        categoryRepo.deleteCategoryByIdAndUserId(categoryId, userId);
    }

    @Override
    public void changeCategoryTitle(Long categoryId, String title, Long userId) {
        Category category = categoryRepo.findByIdAndUserId(categoryId, userId).orElseThrow(() -> {
            throw new CategoryNotFoundException();
        });
        category.changeTitle(title);
        categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories(Long userId) {
        return categoryRepo.findAllByUserId(userId);
    }
}
