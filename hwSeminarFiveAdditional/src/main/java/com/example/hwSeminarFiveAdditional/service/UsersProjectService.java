package com.example.hwSeminarFiveAdditional.service;

import com.example.hwSeminarFiveAdditional.model.Project;
import com.example.hwSeminarFiveAdditional.model.User;
import com.example.hwSeminarFiveAdditional.model.UsersProject;
import com.example.hwSeminarFiveAdditional.repository.ProjectRepository;
import com.example.hwSeminarFiveAdditional.repository.UserRepository;
import com.example.hwSeminarFiveAdditional.repository.UsersProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Log
public class UsersProjectService {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private UsersProjectRepository usersProjectRepository;

    public List<User> getUsersByProjectId(Long projectId) {
        return userRepository.findAllById(usersProjectRepository.findAllUserIdByProjectId(projectId));
    }

    public List<Project> getProjectsByUserId(Long userId) {
        return projectRepository.findAllById(usersProjectRepository.findAllProjectIdByUserId(userId));
    }

    public void addUserToProject(Long userId, Long projectId) {
        if (userRepository.existsById(userId) && projectRepository.existsById(projectId)) {
            if (usersProjectRepository.findByUserIdAndProjectId(userId, projectId) == null) {
                UsersProject usersProject = new UsersProject();
                usersProject.setUserId(userId);
                usersProject.setProjectId(projectId);
                usersProjectRepository.save(usersProject);
            } else {
                throw new IllegalArgumentException("User is already involved in this project.");
            }
        } else {
            throw new IllegalArgumentException("User or project does not exist.");
        }
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        UsersProject usersProject = usersProjectRepository.findByUserIdAndProjectId(userId, projectId);
        if (usersProject != null) {
            usersProjectRepository.delete(usersProject);
        } else {
            throw new IllegalArgumentException("User is not involved in this project.");
        }

    }
}
