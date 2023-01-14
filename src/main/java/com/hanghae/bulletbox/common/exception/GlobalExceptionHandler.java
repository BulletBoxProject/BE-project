package com.hanghae.bulletbox.common.exception;

import com.hanghae.bulletbox.common.response.Response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Response<?> handle(IllegalArgumentException e){
        return Response.fail(BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Response<?> handle(NoSuchElementException e){
        return Response.fail(BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public Response<?> handle(IllegalStateException e){
        return Response.fail(BAD_REQUEST.value(), e.getMessage(), false);
    }
}
