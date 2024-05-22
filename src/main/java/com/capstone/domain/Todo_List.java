package com.capstone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Todo_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todolist_id;
    private long member_id;
    private long group_id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private double progress;
    private String state;
    private int priority;
}
