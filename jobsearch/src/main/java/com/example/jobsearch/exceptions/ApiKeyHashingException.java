package com.example.jobsearch.exceptions;

public class ApiKeyHashingException extends RuntimeException {

  private final String message;

  public ApiKeyHashingException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
