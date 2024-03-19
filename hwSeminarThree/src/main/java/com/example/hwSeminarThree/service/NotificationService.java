package com.example.hwSeminarThree.service;

import com.example.hwSeminarThree.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notifyUserCreated(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    public void sendNotification(String message) {
        System.out.println(message);
    }
}
