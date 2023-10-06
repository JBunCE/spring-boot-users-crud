package com.example.userscrud.services.impl;

import com.example.userscrud.persistance.entities.Role;
import com.example.userscrud.persistance.entities.User;
import com.example.userscrud.persistance.entities.pivots.UserRole;
import com.example.userscrud.persistance.repositories.IUserRoleRepository;
import com.example.userscrud.services.IRoleService;
import com.example.userscrud.services.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private IUserRoleRepository repository;

    @Autowired
    private IRoleService roleService;

    @Override
    public void addRoles(Set<Long> roleIds, User user) {
        saveRoles(roleIds, user);
    }

    @Override
    public void updateRoles(Set<Long> roleIds, User user) {
        repository.deleteAllByUser(user);
        saveRoles(roleIds, user);
    }

    @Override
    public List<UserRole> findByUser(User user) {
        return repository.findAllByUser(user);
    }

    private void saveRoles(Set<Long> roleIds, User user) {
        List<UserRole> userRoles = roleService.findAllByIds(roleIds).stream()
                .map(role -> toUserRole(role, user))
                .toList();

        repository.saveAll(userRoles);
    }

    private UserRole toUserRole(Role role, User user) {
        UserRole userRole = new UserRole();

        userRole.setRole(role);
        userRole.setUser(user);

        return userRole;
    }
}
