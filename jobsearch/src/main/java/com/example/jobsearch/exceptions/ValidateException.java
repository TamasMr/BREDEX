package com.example.jobsearch.exceptions;

public class ValidateException extends RuntimeException {

  private final String message;

  public ValidateException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}