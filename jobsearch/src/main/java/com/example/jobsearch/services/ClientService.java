package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;

public interface ClientService {

  OutputApiKeyDTO saveClient(InputClientDTO inputClientDto);
}