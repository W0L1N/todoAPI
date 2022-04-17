package com.kacwol.todoAPI.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    List<Category> findAllByUserId(Long id);

    boolean deleteCategoryByIdAndUserId(Long categoryId,Long userId);

    Optional<Category> findByIdAndUserId(Long categoryId, Long userId);
}
