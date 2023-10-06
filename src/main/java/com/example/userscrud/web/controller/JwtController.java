package com.example.userscrud.web.controller;


import com.example.userscrud.configurations.jwt.JwtService;
import com.example.userscrud.web.dtos.BaseResponse;
import com.example.userscrud.web.dtos.request.JwtRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("refresh")
    public ResponseEntity<BaseResponse> refresh(@RequestBody @Valid JwtRequest request) {
        return jwtService.refresh(request.getRefreshToken()).apply();
    }
}
