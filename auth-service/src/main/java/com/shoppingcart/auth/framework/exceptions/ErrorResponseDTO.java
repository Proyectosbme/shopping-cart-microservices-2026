package com.shoppingcart.auth.framework.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {

    private final int status;
    private final String error;
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;
    private final String path;
    private final List<String> details;

    public ErrorResponseDTO(int status, String error, String errorCode, String message, String path) {
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.details = null;
    }

    public ErrorResponseDTO(int status, String error, String errorCode, String message, String path,
            List<String> details) {
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.details = details;
    }
}
