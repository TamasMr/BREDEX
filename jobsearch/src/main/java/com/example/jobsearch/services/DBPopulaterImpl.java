package com.example.jobsearch.services;

import com.example.jobsearch.exceptions.ApiKeyHashingException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DBPopulaterImpl implements DBPopulater {

  private final ClientService clientService;
  private final PositionService positionService;

  @Autowired
  public DBPopulaterImpl(ClientService clientService, PositionService positionService) {
    this.clientService = clientService;
    this.positionService = positionService;
  }

  @EventListener(ApplicationReadyEvent.class)
  @Override
  public void populateDB() throws MalformedURLException {
    positionService.populatePositions();
    try {
      clientService.populateClients();
    } catch (NoSuchAlgorithmException e) {
      throw new ApiKeyHashingException("Internal error, we are working on it!");
    } catch (InvalidKeySpecException e) {
      throw new ApiKeyHashingException("Internal error, we are working on it!");
    }
    System.out.println("App started!");
  }
}