package com.imenez.todolist.repository;

import com.imenez.todolist.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    void deleteTaskById(Long id);

    Optional<TaskModel> findTaskById(Long id);
}
