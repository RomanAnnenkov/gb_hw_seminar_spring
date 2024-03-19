package com.example.hwSeminarThree.service;

import com.example.hwSeminarThree.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private NotificationService notificationService;

    public User createUser(String name, int age, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setEmail(email);

        notificationService.notifyUserCreated(newUser);

        return newUser;
    }
}
