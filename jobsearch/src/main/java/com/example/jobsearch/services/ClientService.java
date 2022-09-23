package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;
import com.example.jobsearch.models.Client;
import java.util.List;

public interface ClientService {

  void addApiKeys(String apiKey, Client client);

  List<String> getApiKeys();

  boolean checkIfApiKeyExists(String apiKey);

  OutputApiKeyDTO saveClient(InputClientDTO inputClientDto);
}