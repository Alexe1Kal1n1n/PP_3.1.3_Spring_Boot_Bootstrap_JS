package com.example.spring_boot.service;

import com.example.spring_boot.model.Role;
import javassist.NotFoundException;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getListRole();

    void add(Role role);

    void update(Role role);

    Role getById(long id);

    Role getByName(String roleName) throws NotFoundException;

    Set<Role> getRoleSet(String[] role) throws NotFoundException;
}
