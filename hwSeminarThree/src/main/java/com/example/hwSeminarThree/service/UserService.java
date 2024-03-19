package com.example.hwSeminarThree.service;

import com.example.hwSeminarThree.domain.User;

public class UserService {
    private NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public User createUser(String name, int age, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setEmail(email);

        notificationService.notifyUserCreated(newUser);

        return newUser;
    }
}
