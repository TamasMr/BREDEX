package com.example.jobsearch.dtos;

public class InputPositionDTO {

  String jobPosition;
  String location;

  public InputPositionDTO() {
  }

  public InputPositionDTO(String jobPosition, String location) {
    this.jobPosition = jobPosition;
    this.location = location;
  }

  public String getJobPosition() {
    return jobPosition;
  }

  public String getLocation() {
    return location;
  }
}
