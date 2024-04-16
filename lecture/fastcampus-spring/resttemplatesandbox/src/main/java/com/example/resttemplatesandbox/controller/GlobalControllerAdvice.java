package com.example.resttemplatesandbox.controller;

import com.example.resttemplatesandbox.exception.UpbitClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UpbitClientException.class)
    public String handeUpbitClientException(UpbitClientException e) {
        return e.getMessage();
    }
}
