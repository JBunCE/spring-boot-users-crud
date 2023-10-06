package com.example.userscrud.web.controller;

import com.example.userscrud.services.IUserService;
import com.example.userscrud.web.dtos.BaseResponse;
import com.example.userscrud.web.dtos.request.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<BaseResponse> getUsers() {
        return userService.getUsers().apply();
    }

    @GetMapping("{userId}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable Long userId) {
        return userService.getUser(userId).apply();
    }

    @PostMapping("create")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserRequest request) {
        return userService.createUser(request).apply();
    }

    @PutMapping("{userId}")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody @Valid UserRequest request, @PathVariable Long userId) {
        return userService.updateUser(request, userId).apply();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId).apply();
    }
}
