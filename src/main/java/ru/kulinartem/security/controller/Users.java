package ru.kulinartem.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kulinartem.security.model.User;
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

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getItemById(id));
        return "user";
    }

    @GetMapping("/new")
    public String showNewUser(@ModelAttribute("newUser") User newUser) {
        return "newUser";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("newUser") User newUser) {
        user.saveItem(newUser);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String showEditUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getItemById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User editedUser, @PathVariable("id") long id) {
        user.updateItem(editedUser, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User deletedUser) {
        user.deleteItem(deletedUser);
        return "redirect:/";
    }
}
