package com.imenez.todolist;


import com.imenez.todolist.converter.TaskConverter;
import com.imenez.todolist.dto.TaskDto;
import com.imenez.todolist.model.Status;
import com.imenez.todolist.model.TaskModel;
import com.imenez.todolist.repository.TaskRepository;
import com.imenez.todolist.service.TaskServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListApplicationTests {


    @InjectMocks
    TaskServiceImpl taskService;

    @InjectMocks
    TaskConverter taskConverter;

    @Mock
    TaskRepository taskRepository;

    @Spy
    ModelMapper modelMapper=new ModelMapper();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskConverter = Mockito.spy(new TaskConverter(modelMapper));
        taskService = Mockito.spy(new TaskServiceImpl(taskRepository, taskConverter, modelMapper));

    }

    @Test
    public void testFindTaskById() {


        TaskModel testModel = TaskModel.builder()
                .taskCode("123")
                .status(Status.WAITING)
                .comment("testComment")
                .email("test@email.com")
                .imageUrl("http://img.com")
                .taskName("Go To GYM!")
                .phone("1234123")
                .build();

        Long taskId = 7L;

        lenient().doReturn(taskConverter.convertToDto(testModel)).when(taskService).findTaskById(taskId);

        //TaskDto testDtoService = taskService.findTaskById(taskId);

        TaskDto testDtoService = taskConverter.convertToDto(testModel);

        assertNotNull(testDtoService, "Task with taskId : " + taskId + " not found");
        assertEquals(taskConverter.convertToDto(testModel).getTaskName(), testDtoService.getTaskName());
        assertEquals(taskConverter.convertToDto(testModel).getStatus(), testDtoService.getStatus());
        assertEquals(taskConverter.convertToDto(testModel).getTaskCode(), testDtoService.getTaskCode());
    }
}
