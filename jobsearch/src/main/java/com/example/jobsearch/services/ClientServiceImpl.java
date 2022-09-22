package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;
import com.example.jobsearch.exceptions.InvalidEmailException;
import com.example.jobsearch.exceptions.InvalidNameException;
import com.example.jobsearch.models.Client;
import com.example.jobsearch.repositories.ClientRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public OutputApiKeyDTO saveUser(InputClientDTO inputClientDto) {
    validateInputClientDto(inputClientDto);
    UUID apiKey = createApiKey();
    Client clientToSave = new Client(inputClientDto.getClientName(), inputClientDto.getClientEmail(), apiKey.toString());
    clientRepository.save(clientToSave);
    return new OutputApiKeyDTO(clientToSave.getApiKey());
  }

  private UUID createApiKey() {
    UUID key = UUID.randomUUID();
    while (clientRepository.existsByApiKey(key.toString())) {
      key = UUID.randomUUID();
    }
    return key;
  }

  private void validateInputClientDto(InputClientDTO inputClientDto) {
    validateName(inputClientDto.getClientName());
    validateEmail(inputClientDto.getClientEmail());
  }

  private void validateEmail(String email) {
    if (email == null || email.isEmpty() || email.isBlank()) {
      throw new InvalidEmailException("Please give an email address!");
    }
    if (clientRepository.existsByEmail(email)) {
      throw new InvalidEmailException("Email already registered!");
    }
    if (!email.matches(
        "([a-zA-Z\\d]+[\\w\\d+~.\\-]*[a-zA-Z\\d]*)*[a-zA-Z\\d]+(@([a-zA-Z\\d]+[a-zA-Z\\d\\-]*[a-zA-Z\\d]*)*[a-zA-Z\\d]\\.([a-zA-Z]+[a-zA-Z-]*[a-zA-Z]+)+)$")
        || email.matches("(.*)[^a-zA-Z\\d]{2,}(.*)")) {
      throw new InvalidEmailException("Please give a valid email address!");
    }
  }

  private void validateName(String name) {
    if (name == null || name.isEmpty() || name.isBlank()) {
      throw new InvalidNameException("Please give your name!");
    }
    if (name.length() > 100) {
      throw new InvalidNameException("Name must be shorter than 100 characters!");
    }
  }
}