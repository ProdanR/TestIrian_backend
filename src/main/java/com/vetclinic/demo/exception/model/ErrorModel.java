package com.vetclinic.demo.exception.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorModel {
    private HttpStatus httpStatus;

    private LocalDateTime localDateTime;

    private String message;

    private String details;

    public ErrorModel(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.localDateTime = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
