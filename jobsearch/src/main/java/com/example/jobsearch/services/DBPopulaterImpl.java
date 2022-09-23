package com.example.jobsearch.services;

import com.example.jobsearch.models.Client;
import com.example.jobsearch.models.Position;
import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.repositories.PositionRepository;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DBPopulaterImpl implements DBPopulater {

  private final PositionRepository positionRepository;
  private final ClientRepository clientRepository;
  private final ClientService clientService;

  @Autowired
  public DBPopulaterImpl(PositionRepository positionRepository, ClientRepository clientRepository, ClientService clientService) {
    this.positionRepository = positionRepository;
    this.clientRepository = clientRepository;
    this.clientService = clientService;
  }

  @EventListener(ApplicationReadyEvent.class)
  @Override
  public void populateDB() throws MalformedURLException {
    populatePositions();
    populateClients();
    System.out.println("App started!");
  }

  private void populateClients() {
    Client client1 = new Client("Sarah Parker", "sarah-parker.work@gmail.cam");
    clientRepository.save(client1);
    clientService.addApiKey("eb9982ff-ef5f-4218-994a-2dacdf9cad08", client1);
    Client client2 = new Client("Edward Parker", "edward-parker.work@gmail.cam");
    clientRepository.save(client2);
    clientService.addApiKey("b8f7d459-5033-47d2-880f-0fedd959ceb5", client2);
    Client client3 = new Client("Blake Johnson", "blake-johnson.home@gmail.can");
    clientRepository.save(client3);
    clientService.addApiKey("70357d90-c858-4b03-b0e2-05e66329f0ce", client3);
    Client client4 = new Client("Gregory Karlberg", "gregory-karlberg.work@gmail.con");
    clientRepository.save(client4);
    clientService.addApiKey("7ee1bb79-9440-4466-aad8-3ac2d5267f91", client4);
    Client client5 = new Client("Adam Sikorsky", "adam-sikorsky.work@gmail.con");
    clientRepository.save(client5);
    clientService.addApiKey("2c44bf66-073d-4f70-a002-9d55b8c04eee", client5);
  }

  private void populatePositions() throws MalformedURLException {
    Position pos1 = new Position("junior java developer", "budapest");
    pos1.setUrl(new URL("https://whatever.com/budapest-junior_java_developer-1"));
    positionRepository.save(pos1);
    Position pos2 = new Position("senior hr manager", "houston");
    pos2.setUrl(new URL("https://whatever.com/houston-senior_hr_manager-2"));
    positionRepository.save(pos2);
    Position pos3 = new Position("junior astronaut", "houston");
    pos3.setUrl(new URL("https://whatever.com/houston-junior_astronaut-3"));
    positionRepository.save(pos3);
    Position pos4 = new Position("senior ufo spotter", "hannover");
    pos4.setUrl(new URL("https://whatever.com/hannover-senior_ufo_spotter-4"));
    positionRepository.save(pos4);
    Position pos5 = new Position("java dev", "braunschweig");
    pos5.setUrl(new URL("https://whatever.com/braunschweig-java_dev-5"));
    positionRepository.save(pos5);
  }
}