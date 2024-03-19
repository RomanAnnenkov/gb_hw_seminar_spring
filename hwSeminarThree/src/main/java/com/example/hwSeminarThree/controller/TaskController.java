package com.example.hwSeminarThree.controller;

import com.example.hwSeminarThree.domain.User;
import com.example.hwSeminarThree.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private DataProcessingService dataProcessingService;

    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    @GetMapping("/sort")
    public List<User> sortUserByAge() {
        return dataProcessingService.sortUsersByAge();
    }

    @GetMapping("/filter/{age}")
    public List<User> filterUserByAge(@PathVariable int age) {
        return dataProcessingService.filterUsersByAge(age);
    }

    @GetMapping("/calc")
    public double calculateAverageAge() {
        return dataProcessingService.calculateAverageAge();
    }

}
