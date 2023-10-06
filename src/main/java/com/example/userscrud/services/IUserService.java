package com.example.userscrud.services;

import com.example.userscrud.persistance.entities.User;
import com.example.userscrud.web.dtos.BaseResponse;
import com.example.userscrud.web.dtos.request.UserRequest;

public interface IUserService {

    BaseResponse getUsers();
    BaseResponse getUser(Long id);
    BaseResponse createUser(UserRequest request);
    BaseResponse updateUser(UserRequest request, Long userId);
    BaseResponse deleteUser(Long id);
    User findByEmail(String name);
}
