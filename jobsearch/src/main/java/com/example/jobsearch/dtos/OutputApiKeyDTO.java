package com.example.jobsearch.dtos;

public class OutputApiKeyDTO {

  String apiKey;

  public OutputApiKeyDTO() {
  }

  public OutputApiKeyDTO(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiKey() {
    return apiKey;
  }
}