package com.imenez.todolist.service;

import com.imenez.todolist.converter.TaskConverter;
import com.imenez.todolist.dto.TaskDto;
import com.imenez.todolist.exception.TaskNotFoundException;
import com.imenez.todolist.model.Status;
import com.imenez.todolist.model.TaskModel;
import com.imenez.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public TaskDto addTask(TaskDto task) {
        task.setTaskCode(UUID.randomUUID().toString());
        if (Objects.isNull(task.getStatus()))
            task.setStatus(Status.WAITING);
        task.setCreatedDate(new Date());
        TaskModel taskModel = taskRepository.save(taskConverter.convertToModel(task));

        TaskDto taskDto = taskConverter.convertToDto(taskModel);

        log.warn(Thread.currentThread().getName() + "-ADDED Task -> " + taskConverter.convertToDto(taskModel).toString());

        return taskDto;
    }

    @Override
    public List<TaskDto> findAllTasks() {

        return taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public void deleteTask(Long id) {

        taskRepository.deleteById(id);
        log.warn(Thread.currentThread().getName() + "-DELETED Task by -> " + id);
    }

    @Transactional
    @Override
    public TaskDto updateTask(Long id, TaskDto task) {

        TaskModel existTask = taskRepository.findTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task couldn't find by id: " + id));

        existTask.setTaskName(task.getTaskName());
        existTask.setComment(task.getComment());
        existTask.setEmail(task.getEmail());
        existTask.setImageUrl(task.getImageUrl());
        existTask.setPhone(task.getPhone());
        existTask.setStatus(task.getStatus());

        TaskDto taskDto = taskConverter.convertToDto(taskRepository.save(existTask));

        log.warn(Thread.currentThread().getName() + "-UPDATED Task -> " + taskDto);

        return taskDto;
    }

    @Override
    public TaskDto findTaskById(Long id) {

        TaskDto taskDto = taskConverter.convertToDto(taskRepository.findTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task by id " + id + " was not found")));

        log.warn(Thread.currentThread().getName() + "-FOUND Task -> " + taskDto);

        return taskDto;
    }
}
