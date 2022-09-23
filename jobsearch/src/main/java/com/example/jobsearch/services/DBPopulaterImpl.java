package com.example.jobsearch.services;

import com.example.jobsearch.models.Client;
import com.example.jobsearch.models.Position;
import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.repositories.PositionRepository;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBPopulaterImpl implements DBPopulater {

  private final ClientRepository clientRepository;
  private final PositionRepository positionRepository;

  @Autowired
  public DBPopulaterImpl(ClientRepository clientRepository, PositionRepository positionRepository) {
    this.clientRepository = clientRepository;
    this.positionRepository = positionRepository;
  }

  public void populateDB() throws MalformedURLException {
    populateClients();
    populatePositions();
  }

  public void populateClients(){
    clientRepository.save(new Client("Sarah Parker", "sarah-parker.work@gmail.cam", "eb9982ff-ef5f-4218-994a-2dacdf9cad08"));
    clientRepository.save(new Client("Edward Parker", "edward-parker.work@gmail.cam", "b8f7d459-5033-47d2-880f-0fedd959ceb5"));
    clientRepository.save(new Client("Blake Johnson", "blake-johnson.home@gmail.can", "70357d90-c858-4b03-b0e2-05e66329f0ce"));
    clientRepository.save(new Client("Gregory Karlberg", "gregory-karlberg.work@gmail.con",
        "7ee1bb79-9440-4466-aad8-3ac2d5267f91"));
    clientRepository.save(new Client("Adam Sikorsky", "adam-sikorsky.work@gmail.con", "2c44bf66-073d-4f70-a002-9d55b8c04eee"));
  }

  public void populatePositions() throws MalformedURLException {
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