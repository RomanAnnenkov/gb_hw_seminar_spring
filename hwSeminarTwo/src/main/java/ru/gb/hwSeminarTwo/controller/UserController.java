package ru.gb.hwSeminarTwo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.hwSeminarTwo.aspects.TrackUserAction;
import ru.gb.hwSeminarTwo.model.User;
import ru.gb.hwSeminarTwo.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController  {
    private final UserService userService;

    @GetMapping("/users")
    @TrackUserAction
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    @TrackUserAction
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("user-create")
    @TrackUserAction
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    @TrackUserAction
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    @TrackUserAction
    public String updateUserForm(User user) {
        return "user-update";
    }

    @PostMapping("user-update")
    @TrackUserAction
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }


}
