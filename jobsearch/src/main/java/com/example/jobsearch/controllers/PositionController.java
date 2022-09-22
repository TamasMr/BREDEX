package com.example.jobsearch.controllers;

import com.example.jobsearch.dtos.InputPositionDTO;
import com.example.jobsearch.dtos.OutputPositionDTO;
import com.example.jobsearch.dtos.OutputUrlDTO;
import com.example.jobsearch.services.PositionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/position")
public class PositionController {

  private final PositionService positionService;

  @Autowired
  public PositionController(PositionService positionService) {
    this.positionService = positionService;
  }

  @PostMapping("")
  public ResponseEntity<OutputUrlDTO> createNewPosition(@RequestBody InputPositionDTO newPosition, @RequestParam String apiKey) {
    return ResponseEntity.status(201).body(positionService.savePosition(apiKey, newPosition));
  }

  @GetMapping("/search")
  public ResponseEntity<List<OutputPositionDTO>> searchForPositions(@RequestParam(required = false) String keyWord,
      @RequestParam(required = false) String location, @RequestParam String apiKey) {
    return ResponseEntity.status(200).body(positionService.searchForPositions(apiKey, keyWord, location));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OutputPositionDTO> findPosition(@PathVariable int id) {
    return ResponseEntity.status(200).body(positionService.findPosition(id));
  }
}