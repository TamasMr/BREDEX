package com.example.jobsearch.models;

import java.net.URL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String jobPosition;

  @Column(length = 50)
  private String location;

  @Column(unique = true)
  private URL url;

  public Position() {
  }

  public Position(String jobPosition, String location) {
    this.jobPosition = jobPosition;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public String getJobPosition() {
    return jobPosition;
  }

  public String getLocation() {
    return location;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }
}