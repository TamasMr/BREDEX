package com.example.jobsearch.services;

import java.net.MalformedURLException;

public interface DBPopulater {

  public void populateDB() throws MalformedURLException;

  public void populateClients();

  public void populatePositions() throws MalformedURLException;
}
