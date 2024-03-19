package com.example.hwSeminarThree.controller;

import com.example.hwSeminarThree.domain.User;
import com.example.hwSeminarThree.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<User> getUserList() {
        return registrationService
                .getDataProcessingService()
                .getUsers();
    }

    @PostMapping("/body")
    public String addUser(@RequestBody User user) {
        registrationService.getDataProcessingService().addUser(user);
        return "User added from body";
    }
}
