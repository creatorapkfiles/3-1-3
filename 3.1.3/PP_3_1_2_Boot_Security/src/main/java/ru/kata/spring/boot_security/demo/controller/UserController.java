package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/admin")
    public String findAll(Model model) {
        List<User> users = userServiceImpl.allUser();
        model.addAttribute("users", users);
        return "users";
    }
//    @GetMapping("/user")
//    public String finnById(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userService.findById(id));
//        return "user";
//    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        User user = userServiceImpl.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/create")
    public String createUser(User user) {
        return "/create";
    }
    @PostMapping("/admin/create")
    public String create(User user) {
        userServiceImpl.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userServiceImpl.removeUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/update")
    public String updateUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "update";
    }
    @PostMapping("/admin/update")
    public String update (@ModelAttribute("user") User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

}
