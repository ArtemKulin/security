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
    public String showAdminRootPage(Model model) {
        model.addAttribute("users", user.getAllItems());
        return "admin/users";
    }

    @GetMapping("/users")
    public String showAllUsersPage(Model model) {
        model.addAttribute("users", user.getAllItems());
        return "admin/users";
    }

    @GetMapping("/{id}")
    public String showUserPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getItemById(id));
        return "admin/user";
    }

    @GetMapping("/new")
    public String showNewUserPage(@ModelAttribute("newUser") User newUser) {
        return "admin/newUser";
    }

    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User newUser) {
        user.saveItem(newUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.getItemById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User editedUser, @PathVariable("id") long id) {
        user.updateItem(editedUser, id);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User deletedUser) {
        user.deleteItem(deletedUser);
        return "redirect:/admin/";
    }
}
