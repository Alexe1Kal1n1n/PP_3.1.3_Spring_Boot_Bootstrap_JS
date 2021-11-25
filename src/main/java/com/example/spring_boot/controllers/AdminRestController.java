package com.example.spring_boot.controllers;

import com.example.spring_boot.model.Role;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.RoleService;
import com.example.spring_boot.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list")  //при переходе на http://localhost:8080/api/admin/list получаем json всех юзеров
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping()  //пост запрос на http://localhost:8080/api/admin добавление юзера из полученого json
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        userService.saveUser(user);
        return new ResponseEntity<>(user,headers,HttpStatus.CREATED);
    }

    @GetMapping("/roles")  //при переходе на http://localhost:8080/api/admin/roles получаем json всех ролей
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")  //при переходе на http://localhost:8080/api/admin/{id} получаем json конкретного юзера
    public ResponseEntity<User> getUserId(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update") //пут запрос на http://localhost:8080/api/admin/update обновление юзера из полученого json
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")  //делит запрос на http://localhost:8080/api/admin/{id} получаем json конкретного юзера и удаляем его
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}