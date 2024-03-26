package ru.gb.hwSeminarTwo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.hwSeminarTwo.model.User;
import ru.gb.hwSeminarTwo.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }
}
