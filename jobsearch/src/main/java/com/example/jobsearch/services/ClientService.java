package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;
import com.example.jobsearch.models.Client;

public interface ClientService {

  boolean checkIfApiKeyExists(String apiKey);

  OutputApiKeyDTO saveClient(InputClientDTO inputClientDto);

  public void populateClients();
}