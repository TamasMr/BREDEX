package com.example.jobsearch.models;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String clientName;
  @Column(length = 100, unique = true)
  private String email;

  public Client() {
  }

  public Client(String clientName, String email) {
    this.clientName = clientName;
    this.email = email;
  }
}