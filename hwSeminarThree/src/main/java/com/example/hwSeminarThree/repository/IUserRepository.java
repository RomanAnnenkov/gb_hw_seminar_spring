package com.example.hwSeminarThree.repository;

import com.example.hwSeminarThree.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserRepository {
    List<User> getUsers();
    void addUser(User user);
}
