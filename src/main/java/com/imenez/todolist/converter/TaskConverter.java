package com.imenez.todolist.converter;

import com.imenez.todolist.dto.TaskDto;
import com.imenez.todolist.model.TaskModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final ModelMapper modelMapper;


    public TaskModel convertToModel(TaskDto taskDto) {

        return TaskModel.builder()
                .id(taskDto.getId())
                .taskName(taskDto.getTaskName())
                .comment(taskDto.getComment())
                .email(taskDto.getEmail())
                .imageUrl(taskDto.getImageUrl())
                .phone(taskDto.getPhone())
                .status(taskDto.getStatus())
                .taskCode(taskDto.getTaskCode())
                .createdDate(taskDto.getCreatedDate())
                .build();
    }

    public TaskDto convertToDto(TaskModel taskModel) {

        return modelMapper.map(taskModel, TaskDto.class);
    }
}
