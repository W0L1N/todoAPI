package com.kacwol.todoAPI.category;

import java.util.List;

public interface CategoryService {

    boolean addCategory(CategoryDto category, Long userId);

    boolean deleteCategory(Long categoryId, Long userId);

    boolean changeCategoryTitle(Long categoryId,String title,Long userId);

    List<Category> getAllCategories(Long userId);
}
