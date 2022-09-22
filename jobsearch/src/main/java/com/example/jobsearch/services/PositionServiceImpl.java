package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputPositionDTO;
import com.example.jobsearch.dtos.OutputPositionDTO;
import com.example.jobsearch.dtos.OutputUrlDTO;
import com.example.jobsearch.exceptions.InvalidApiKeyException;
import com.example.jobsearch.exceptions.InvalidJobPositionException;
import com.example.jobsearch.exceptions.InvalidLocationException;
import com.example.jobsearch.exceptions.InvalidPositionSearchException;
import com.example.jobsearch.models.Position;
import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.repositories.PositionRepository;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final ClientRepository clientRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public PositionServiceImpl(PositionRepository positionRepository, ClientRepository clientRepository) {
    this.positionRepository = positionRepository;
    this.clientRepository = clientRepository;
  }

  @Override
  public OutputUrlDTO savePosition(String apiKey, InputPositionDTO newPosition) {
    validateApiKey(apiKey);
    validateJobPosition(newPosition.getJobPosition());
    validateJobLocation(newPosition.getLocation());
    Position positionToSave = new Position(newPosition.getJobPosition().replaceAll("\\s{2,}", " ").toLowerCase(),
        newPosition.getLocation().replaceAll("\\s{2,}", " ").toLowerCase());
    positionRepository.save(positionToSave);
    URL url;
    try {
      url = new URL(
          "https://whatever.com/" + positionToSave.getLocation().replace(" ", "_") + "-" + positionToSave.getJobPosition().replace(" ", "_")
              + "-" + positionToSave.getId());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    positionToSave.setUrl(url);
    positionRepository.save(positionToSave);
    return new OutputUrlDTO(url);
  }

  private void validateJobLocation(String location) {
    if (location.length() > 50) {
      throw new InvalidLocationException("The location can be 50 characters maximum!");
    }
  }

  private void validateJobPosition(String position) {
    if (position.length() > 50) {
      throw new InvalidJobPositionException("The position can be 50 characters maximum!");
    }
  }

  private void validateApiKey(String apiKey) {
    if (!clientRepository.existsByApiKey(apiKey)) {
      throw new InvalidApiKeyException("Wrong Api Key!");
    }
  }

  @Override
  public List<OutputPositionDTO> searchForPositions(String apiKey, String keyWord, String location) {
    validateApiKey(apiKey);
    if (keyWord != null) {
      keyWord = keyWord.replaceAll("\\s{2,}", " ").toLowerCase();
    }
    if (location != null) {
      location = location.replaceAll("\\s{2,}", " ").toLowerCase();
    }
    if (keyWord == null || keyWord.isEmpty() || keyWord.isBlank()) {
      return convertToOutputPositionsDto(positionRepository.findAllByLocation(location));
    } else if (location == null || location.isEmpty() || location.isBlank()) {
      return convertToOutputPositionsDto(positionRepository.findAllByKeyWord(keyWord));
    } else {
      return convertToOutputPositionsDto(positionRepository.findAllByLocationAndKeyWord(location, keyWord));
    }
  }

  private List<OutputPositionDTO> convertToOutputPositionsDto(List<Position> positions) {
    return positions.stream()
        .map(position -> modelMapper.map(position, OutputPositionDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public OutputPositionDTO findPosition(long id) {
    Position position = positionRepository.findById(id).orElseThrow(() -> new InvalidPositionSearchException("No position with that id"));
    return modelMapper.map(position, OutputPositionDTO.class);
  }
}