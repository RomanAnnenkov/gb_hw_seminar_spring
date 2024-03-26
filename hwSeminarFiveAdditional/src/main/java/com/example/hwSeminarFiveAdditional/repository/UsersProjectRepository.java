package com.example.hwSeminarFiveAdditional.repository;

import com.example.hwSeminarFiveAdditional.model.UsersProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    @Query("SELECT userId FROM UsersProject WHERE projectId = :projectId")
    List<Long> findAllUserIdByProjectId(Long projectId);

    @Query("SELECT projectId FROM UsersProject WHERE userId = :userId")
    List<Long> findAllProjectIdByUserId(Long userId);

    UsersProject findByUserIdAndProjectId(Long userId, Long projectId);
}
