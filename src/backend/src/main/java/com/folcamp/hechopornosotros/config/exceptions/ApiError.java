package com.folcamp.hechopornosotros.config.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ApiError {
    private int code;
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ApiError(int code, HttpStatus status, String message, Map<String, String> errors) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
