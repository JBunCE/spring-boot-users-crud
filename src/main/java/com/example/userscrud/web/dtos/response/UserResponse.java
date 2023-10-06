package com.example.userscrud.web.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Boolean enabled;
    private List<String> roles;
}
