package com.example.hwSeminarFiveAdditional.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_project_relation")
public class UsersProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;
}
