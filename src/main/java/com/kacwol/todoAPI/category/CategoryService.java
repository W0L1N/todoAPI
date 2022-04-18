package com.kacwol.todoAPI.category;

import java.util.List;

public interface CategoryService {

    Category getCategory(Long categoryId,Long userId);

    void addCategory(CategoryDto category, Long userId);

    void deleteCategory(Long categoryId, Long userId);

    void changeCategoryTitle(Long categoryId,String title,Long userId);

    List<Category> getAllCategories(Long userId);
}
