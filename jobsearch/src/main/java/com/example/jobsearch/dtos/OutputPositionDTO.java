package com.example.jobsearch.dtos;

import java.net.URL;

public class OutputPositionDTO {

  private URL url;

  public OutputPositionDTO() {
  }

  public OutputPositionDTO(URL url) {
    this.url = url;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }
}