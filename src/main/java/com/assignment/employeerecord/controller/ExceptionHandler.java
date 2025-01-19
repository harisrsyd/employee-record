package com.assignment.employeerecord.controller;

import com.assignment.employeerecord.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandler {
   @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<WebResponse<String>> constraintValidationHandler(ConstraintViolationException e) {
      return ResponseEntity.badRequest().body(WebResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).error(e.getMessage()).build());
   }
   
   @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
   public ResponseEntity<WebResponse<String>> responseStatusExceptionHandler(ResponseStatusException e) {
      return ResponseEntity.status(e.getStatusCode()).body(WebResponse.<String>builder().status(e.getStatusCode().value()).error(e.getReason()).build());
   }
}
