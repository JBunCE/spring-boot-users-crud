package com.example.userscrud.services;

import com.example.userscrud.persistance.entities.User;
import com.example.userscrud.persistance.entities.pivots.UserRole;

import java.util.List;
import java.util.Set;

public interface IUserRoleService {
    void addRoles(Set<Long> roleIds, User userId);

    void updateRoles(Set<Long> roleIds, User userId);

    List<UserRole> findByUser(User user);
}
