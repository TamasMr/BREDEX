package com.example.jobsearch.exceptions;

import com.example.jobsearch.dtos.SimpleErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidateException.class)
  public ResponseEntity<String> handleValidateException(ValidateException validateException) {
    return new ResponseEntity(new SimpleErrorDTO(validateException.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidPositionSearchException.class)
  public ResponseEntity<String> handleInvalidPositionSearchException(InvalidPositionSearchException invalidPositionSearchException) {
    return new ResponseEntity(new SimpleErrorDTO(invalidPositionSearchException.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ApiKeyHashingException.class)
  public ResponseEntity<String> handleApiKeyHashingException(ApiKeyHashingException apiKeyHashingException) {
    return new ResponseEntity(new SimpleErrorDTO(apiKeyHashingException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}