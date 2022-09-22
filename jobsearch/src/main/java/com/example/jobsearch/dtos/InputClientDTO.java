package com.example.jobsearch.dtos;

public class InputClientDTO {

  private String clientName;
  private String clientEmail;

  public InputClientDTO() {
  }

  public InputClientDTO(String clientName, String clientEmail) {
    this.clientName = clientName;
    this.clientEmail = clientEmail;
  }

  public String getClientName() {
    return clientName;
  }

  public String getClientEmail() {
    return clientEmail;
  }
}