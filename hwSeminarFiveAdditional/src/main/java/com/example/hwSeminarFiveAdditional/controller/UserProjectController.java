package com.example.hwSeminarFiveAdditional.controller;

import com.example.hwSeminarFiveAdditional.model.Project;
import com.example.hwSeminarFiveAdditional.model.User;
import com.example.hwSeminarFiveAdditional.service.UsersProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserProjectController {
    private UsersProjectService usersProjectService;

    @GetMapping("/project-users/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = usersProjectService.getUsersByProjectId(projectId);
        if (users.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/user-projects/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = usersProjectService.getProjectsByUserId(userId);
        if (projects.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(projects);
        }
    }


    @PostMapping("/add")
    public ResponseEntity addUserToProject(@RequestParam(name = "user-id") Long userId, @RequestParam(name = "project-id") Long projectId) {
        try {
            usersProjectService.addUserToProject(userId, projectId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/delete")
    public ResponseEntity removeUserFromProject(@RequestParam(name = "user-id") Long userId, @RequestParam(name = "project-id") Long projectId) {
        try {
            usersProjectService.removeUserFromProject(userId, projectId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
