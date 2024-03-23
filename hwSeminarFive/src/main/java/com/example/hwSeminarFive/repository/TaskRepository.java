package com.example.hwSeminarFive.repository;

import com.example.hwSeminarFive.model.Task;
import com.example.hwSeminarFive.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllTasksByStatus(TaskStatus status);
}
