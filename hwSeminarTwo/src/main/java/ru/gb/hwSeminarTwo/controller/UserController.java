package ru.gb.hwSeminarTwo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.hwSeminarTwo.model.User;
import ru.gb.hwSeminarTwo.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class UserController  {
    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        log.info("GET: /users");
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("GET: /user-create");
        return "user-create";
    }

    @PostMapping("user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        log.info("POST: user-create " + user.getFirstName() + " " + user.getLastName());
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        log.info("GET: /user-delete/" + id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(User user) {
        log.info("GET: /user-update/" + user.getId());
        return "user-update";
    }

    @PostMapping("user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        log.info("POST: user-update " + user);
        return "redirect:/users";
    }


}
