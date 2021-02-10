package ru.kulinartem.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kulinartem.security.model.User;
import ru.kulinartem.security.service.UserService;

@Controller
@RequestMapping("/admin")
public class Admins {

    private final UserService user;

    @Autowired
    public Admins(UserService user) {
        this.user = user;
    }

    @GetMapping("/")
    public String showRootPage(Model model) {
        model.addAttribute("users", user.getAllItems());
        return "admin/users";
    }

    @GetMapping("/users")
    public String showAllUsersPage(Model model) {
        model.addAttribute("users", user.getAllItems());
        return "admin/users";
    }

//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", user.getItemById(id));
//        return "user/user";
//    }

    @GetMapping("/new")
    public String showNewUser(@ModelAttribute("newUser") User newUser) {
        return "admin/newUser";
    }

    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User newUser) {
        user.saveItem(newUser);
        return "redirect:/admin/users";
    }
}
