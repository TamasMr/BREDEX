package com.example.jobsearch.controllers;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;
import com.example.jobsearch.services.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping("")
  @ApiOperation(
      value = "Creates a new Client, returns with api key.",
      notes = "Please provide Client information in json."
  )
  public ResponseEntity<OutputApiKeyDTO> createNewClient(@RequestBody InputClientDTO newClient) {
    return ResponseEntity.status(201).body(clientService.saveClient(newClient));
  }
}