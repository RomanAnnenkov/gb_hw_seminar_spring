package com.example.hwSeminarThree.service;

import com.example.hwSeminarThree.domain.User;
import com.example.hwSeminarThree.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DataProcessingService {

    @Autowired
    @Qualifier("repositoryH2")
    private IUserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public List<User> sortUsersByAge() {
        return userRepository.getUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .toList();
    }

    public List<User> filterUsersByAge(int age) {
        return userRepository.getUsers()
                .stream()
                .filter(user -> user.getAge() > age)
                .toList();
    }

    public double calculateAverageAge() {
        return userRepository.getUsers()
                .stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }
}
