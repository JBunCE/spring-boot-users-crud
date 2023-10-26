package com.example.userscrud.configurations.user;

import lombok.Data;

@Data
public class UserAuthDto {
    private String email;
    private String password;
}
