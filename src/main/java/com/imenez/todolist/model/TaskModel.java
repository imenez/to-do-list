package com.imenez.todolist.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "T_TASKS")
@Builder
public class TaskModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String taskCode;

    @Column(nullable = false, unique = true)
    private String taskName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    @Column(length = 100000)
    private String comment;


    @Column(unique = true)
    private String email;


    private String phone;

    private String imageUrl;

    private Date createdDate;


}
