package com.mood.bibliothequemood.handlers;

import com.mood.bibliothequemood.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> erroMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erroMap.put(error.getField(), error.getDefaultMessage());
        });
        return erroMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookNotFoundException.class)
    public Map<String, String> handlerBusinessException(BookNotFoundException ex) {
        Map<String, String> erroMap = new HashMap();
        erroMap.put("errorMessage", ex.getMessage());
        return erroMap;
    }
}
