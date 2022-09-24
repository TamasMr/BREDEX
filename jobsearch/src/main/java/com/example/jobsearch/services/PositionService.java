package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputPositionDTO;
import com.example.jobsearch.dtos.OutputPositionDTO;
import com.example.jobsearch.dtos.OutputUrlDTO;
import java.net.MalformedURLException;
import java.util.List;

public interface PositionService {

  OutputUrlDTO savePosition(String apiKey, InputPositionDTO newPosition);

  List<OutputPositionDTO> searchForPositions(String apiKey, String keyWord, String location);

  OutputPositionDTO findPosition(long id);

  public void populatePositions() throws MalformedURLException;
}