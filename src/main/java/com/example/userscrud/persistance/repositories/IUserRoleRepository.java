package com.example.userscrud.persistance.repositories;

import com.example.userscrud.persistance.entities.User;
import com.example.userscrud.persistance.entities.pivots.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

    void deleteAllByUser(User user);

    List<UserRole> findAllByUser(User user);
}
