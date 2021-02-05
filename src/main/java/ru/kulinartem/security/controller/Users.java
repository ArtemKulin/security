package ru.kulinartem.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kulinartem.security.service.UserService;

@Controller
public class Users {

    private final UserService user;

    @Autowired
    public Users(UserService user) {
        this.user = user;
    }

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", user.getAllItems());
        return "index";
    }

//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", user.getItemById(id));
//        return "user";
//    }

}
