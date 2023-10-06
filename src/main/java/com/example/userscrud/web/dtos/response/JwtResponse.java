package com.example.userscrud.web.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {
    private String token;
    private String refreshToken;
}
