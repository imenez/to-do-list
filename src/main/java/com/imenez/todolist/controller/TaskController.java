package com.imenez.todolist.controller;


import com.imenez.todolist.dto.TaskDto;
import com.imenez.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {


    private final TaskService taskService;


    @GetMapping("/find-all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {

        return ResponseEntity.ok(taskService.findAllTasks());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(taskService.findTaskById(id), HttpStatus.FOUND);
    }


    @PostMapping("/add")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.updateTask(id, taskDto), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok("Task was deleted");
    }


}
