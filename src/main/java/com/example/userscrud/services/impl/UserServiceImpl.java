package com.example.userscrud.services.impl;

import com.example.userscrud.persistance.entities.User;
import com.example.userscrud.persistance.repositories.IUserRepository;
import com.example.userscrud.services.IRoleService;
import com.example.userscrud.services.IUserRoleService;
import com.example.userscrud.services.IUserService;
import com.example.userscrud.web.dtos.BaseResponse;
import com.example.userscrud.web.dtos.request.UserRequest;
import com.example.userscrud.web.dtos.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse getUsers() {
        List<UserResponse> responseList = repository.findAll()
                .stream().map(this::toUserResponse).toList();

        return BaseResponse.builder()
                .data(responseList)
                .message("Users found")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse getUser(Long id) {
        User response = repository.findById(id).orElseThrow(RuntimeException::new);

        return BaseResponse.builder()
                .data(toUserResponse(response))
                .message("User found with id: " + id)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse createUser(UserRequest request) {
        User savedUser = repository.save(toUser(request));
        userRoleService.addRoles(request.getRoleIds(), savedUser);

        return BaseResponse.builder()
                .data(toUserResponse(savedUser))
                .message("The user was created with id: " + savedUser.getId())
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse updateUser(UserRequest request, Long userId) {
        User user = repository.findById(userId).orElseThrow(RuntimeException::new);

        update(user, request);
        userRoleService.updateRoles(request.getRoleIds(), user);

        return BaseResponse.builder()
                .data(toUserResponse(user))
                .message("The user with id: " + userId + " was updated successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(RuntimeException::new);

        repository.delete(user);

        return BaseResponse.builder()
                .message("The user with id: " + id + " was deleted successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    private void update(User user, UserRequest request) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.getEnabled());
    }

    private User toUser(UserRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.getEnabled());

        return user;
    }

    private UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setEnabled(user.getEnabled());

        List<String> roles = userRoleService.findByUser(user)
                .stream().map(userRole -> userRole.getRole().getName())
                .toList();

        response.setRoles(roles);

        return response;
    }
}
