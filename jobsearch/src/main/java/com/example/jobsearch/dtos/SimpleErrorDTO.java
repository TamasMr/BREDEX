package com.example.jobsearch.dtos;

public class SimpleErrorDTO {

  private String error;

  public SimpleErrorDTO() {
  }

  public SimpleErrorDTO(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }
}