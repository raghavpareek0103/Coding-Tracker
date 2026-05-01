package com.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles @Valid validation errors (blank fields, invalid email, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handles user not found (when login email doesn't exist)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            NoSuchElementException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "User not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handles invalid JWT or unauthorized access
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Map<String, String>> handleSecurity(
            SecurityException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Unauthorized - " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Handles any other unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(
            Exception ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Something went wrong - " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}