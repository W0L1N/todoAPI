package com.kacwol.todoAPI.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);
}

