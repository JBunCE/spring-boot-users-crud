package com.example.userscrud.services;

import com.example.userscrud.persistance.entities.Role;

import java.util.Collection;
import java.util.List;

public interface IRoleService {
    List<Role> findAllByIds(Collection<Long> id);
}
