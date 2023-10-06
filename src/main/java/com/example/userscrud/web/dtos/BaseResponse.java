package com.example.userscrud.web.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder @Getter
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus status;
    private Integer statusCode;

    public ResponseEntity<BaseResponse> apply() {
        return new ResponseEntity<>(this, status);
    }
}
