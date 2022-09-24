package com.example.jobsearch.services;

import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DBPopulaterImpl implements DBPopulater {

  private final ClientServiceImpl clientService;
  private final PositionService positionService;

  @Autowired
  public DBPopulaterImpl(ClientServiceImpl clientService, PositionService positionService) {
    this.clientService = clientService;
    this.positionService = positionService;
  }

  @EventListener(ApplicationReadyEvent.class)
  @Override
  public void populateDB() throws MalformedURLException {
    positionService.populatePositions();
    clientService.populateClients();
    System.out.println("App started!");
  }
}