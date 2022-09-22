package com.example.jobsearch.exceptions;

public class InvalidPositionSearchException extends RuntimeException {

  private final String message;

  public InvalidPositionSearchException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}