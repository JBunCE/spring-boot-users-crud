package com.example.userscrud.persistance.repositories;

import com.example.userscrud.persistance.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByIdIn(Collection<Long> id);

}
