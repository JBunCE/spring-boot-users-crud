package com.example.userscrud.services.impl;

import com.example.userscrud.persistance.entities.Role;
import com.example.userscrud.persistance.repositories.IRoleRepository;
import com.example.userscrud.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository repository;

    @Override
    public List<Role> findAllByIds(Collection<Long> roleIds) {
        return repository.findAllByIdIn(roleIds);
    }
}
