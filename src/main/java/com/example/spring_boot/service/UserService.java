package com.example.spring_boot.service;

import javassist.NotFoundException;
import com.example.spring_boot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getById(long id);

    void update(User user);

    void delete(User user);

    User getByName(String userName) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;
}
