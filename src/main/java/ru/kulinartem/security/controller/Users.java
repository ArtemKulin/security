package ru.kulinartem.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kulinartem.security.model.User;
import ru.kulinartem.security.service.UserService;

@Controller
@RequestMapping("/user")
public class Users {

    private final UserService user;

    @Autowired
    public Users(UserService user) {
        this.user = user;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getItemById(id));
        return "user/user";
    }
}
