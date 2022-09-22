package com.example.jobsearch.exceptions;

public class InvalidEmailException extends ValidateException {

  public InvalidEmailException(String message) {
    super(message);
  }
}