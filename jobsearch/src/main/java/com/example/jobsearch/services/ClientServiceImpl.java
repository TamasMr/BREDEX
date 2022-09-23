package com.example.jobsearch.services;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.dtos.OutputApiKeyDTO;
import com.example.jobsearch.exceptions.InvalidEmailException;
import com.example.jobsearch.exceptions.InvalidNameException;
import com.example.jobsearch.models.Client;
import com.example.jobsearch.repositories.ClientRepository;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private HashMap<String, Client> apiKeys = new HashMap<>();

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  private void populateDBWithClients() {
    Client client1 = new Client("Sarah Parker", "sarah-parker.work@gmail.cam");
    clientRepository.save(client1);
    apiKeys.put("eb9982ff-ef5f-4218-994a-2dacdf9cad08", client1);
    Client client2 = new Client("Edward Parker", "edward-parker.work@gmail.cam");
    clientRepository.save(client2);
    apiKeys.put("b8f7d459-5033-47d2-880f-0fedd959ceb5", client2);
    Client client3 = new Client("Blake Johnson", "blake-johnson.home@gmail.can");
    clientRepository.save(client3);
    apiKeys.put("70357d90-c858-4b03-b0e2-05e66329f0ce", client3);
    Client client4 = new Client("Gregory Karlberg", "gregory-karlberg.work@gmail.con");
    clientRepository.save(client4);
    apiKeys.put("7ee1bb79-9440-4466-aad8-3ac2d5267f91", client4);
    Client client5 = new Client("Adam Sikorsky", "adam-sikorsky.work@gmail.con");
    clientRepository.save(client5);
    apiKeys.put("2c44bf66-073d-4f70-a002-9d55b8c04eee", client5);
  }

  @Override
  public OutputApiKeyDTO saveClient(InputClientDTO inputClientDto) {
    validateInputClientDto(inputClientDto);
    UUID apiKey = createApiKey();
    Client clientToSave = new Client(inputClientDto.getClientName(), inputClientDto.getClientEmail());
    clientRepository.save(clientToSave);
    apiKeys.put(apiKey.toString(), clientToSave);
    return new OutputApiKeyDTO(apiKey.toString());
  }

  private UUID createApiKey() {
    UUID key = UUID.randomUUID();
    while (checkIfApiKeyExists(key.toString())) {
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

  @Override
  public boolean checkIfApiKeyExists(String apiKey) {
    return apiKeys.containsKey(apiKey);
  }
}