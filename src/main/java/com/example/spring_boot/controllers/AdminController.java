package com.example.spring_boot.controllers;

import com.example.spring_boot.model.Role;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.RoleService;
import com.example.spring_boot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUser(Model model, @AuthenticationPrincipal User user) {
        List<Role> allRole = roleService.getListRole();
        List<User> allUsers = userService.getListUsers();
        model.addAttribute("allRoles", allRole);
        model.addAttribute("allUs", allUsers);
        model.addAttribute("user", user);
        return "admin-page";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user, @RequestParam("rolles") String[] role) throws NotFoundException {
        user.setRoles(roleService.getRoleSet(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam("rolles") String[] role) throws NotFoundException{
        user.setRoles(roleService.getRoleSet(role));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}