package com.imenez.todolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imenez.todolist.model.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {

    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    @NotEmpty(message = "Task code shouldn't be null")
    private String taskCode;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Task name shouldn't be null")
    private String taskName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    @Column(length = 100000)
    private String comment;

    @NotEmpty
    @Email(message = "E-mail must be valid.")
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be valid.")
    private String phone;

    private String imageUrl;

    private Date createdDate;
}
