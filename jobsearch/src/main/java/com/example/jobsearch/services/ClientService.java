package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;

public interface ClientService {

  boolean checkIfApiKeyExists(String apiKey);

  OutputApiKeyDTO saveClient(InputClientDTO inputClientDto);

  void populateClients();
}