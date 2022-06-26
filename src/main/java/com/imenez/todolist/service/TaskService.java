package com.imenez.todolist.service;

import com.imenez.todolist.dto.TaskDto;

import java.util.List;


public interface TaskService {

    TaskDto addTask(TaskDto task);

    List<TaskDto> findAllTasks();

    void deleteTask(Long id);

    TaskDto updateTask(Long id, TaskDto task);

    TaskDto findTaskById(Long id);
}
