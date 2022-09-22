package com.example.jobsearch.dtos;

import java.net.URL;

public class OutputUrlDTO {

  private URL url;

  public OutputUrlDTO() {
  }

  public OutputUrlDTO(URL url) {
    this.url = url;
  }

  public URL getUrl() {
    return url;
  }
}