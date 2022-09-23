package com.example.jobsearch.services;

import com.example.jobsearch.models.Position;
import com.example.jobsearch.repositories.PositionRepository;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBPopulaterImpl implements DBPopulater {

  private final PositionRepository positionRepository;

  @Autowired
  public DBPopulaterImpl(PositionRepository positionRepository) {
    this.positionRepository = positionRepository;
  }

  public void populateDB() throws MalformedURLException {
    populatePositions();
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