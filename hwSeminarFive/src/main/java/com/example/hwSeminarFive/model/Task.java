package com.example.hwSeminarFive.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private TaskStatus status = TaskStatus.NOT_STARTED;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();
}
