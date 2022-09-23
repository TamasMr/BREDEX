package com.example.jobsearch.unittests;

import static org.junit.jupiter.api.Assertions.*;

import com.example.jobsearch.dtos.InputClientDTO;
import com.example.jobsearch.exceptions.InvalidEmailException;
import com.example.jobsearch.exceptions.InvalidNameException;
import com.example.jobsearch.models.Client;
import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.services.ClientService;
import com.example.jobsearch.services.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ClientServiceImplTest {

  private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
  private final ClientService clientService = new ClientServiceImpl(clientRepository);

  @Test
  public void createUser_WithValidInputs_ShouldSaveEntityOnce() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt-elek@gmail.com");
    clientService.saveClient(inputClientDTO);
    Mockito.verify(clientRepository, Mockito.times(1)).save(Mockito.any(Client.class));
  }

  @Test
  public void createUser_WithNullName_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO(null, "teszt-elek@gmail.com");
    Throwable exception = assertThrows (InvalidNameException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give your name!", exception.getMessage());
  }

  @Test
  public void createUser_WithEmptyName_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("", "teszt-elek@gmail.com");
    Throwable exception = assertThrows (InvalidNameException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give your name!", exception.getMessage());
  }

  @Test
  public void createUser_WithBlankName_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("  ", "teszt-elek@gmail.com");
    Throwable exception = assertThrows (InvalidNameException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give your name!", exception.getMessage());
  }

  @Test
  public void createUser_WithTooLongName_ShouldThrowException() {
    StringBuilder stringBuilder = new StringBuilder();
    while (stringBuilder.length()<=100){
      stringBuilder.append("a");
    }
    String name = stringBuilder.toString();
    InputClientDTO inputClientDTO = new InputClientDTO(name, "teszt-elek@gmail.com");
    Throwable exception = assertThrows (InvalidNameException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Name must be shorter than 100 characters!", exception.getMessage());
  }

  @Test void createUser_WithExistingEmail_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt-elek@gmail.com");
    String email = inputClientDTO.getClientEmail();
    Mockito.when(clientRepository.existsByEmail(email)).thenReturn(true);
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Email already registered!", exception.getMessage());
  }

  @Test void createUser_WithInvalidCharacterInEmailAddress_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt#elek@gmail.com");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithInvalidCharacterInEmailDomain_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "tesztelek@gma+il.com");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithInvalidCharacterInEmailTopLevelDomain_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt-elek@gmail.co~m");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithTwoAtInEmail_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt@elek@gmail.com");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithTwoSpecialCharactersInARowInEmail_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt--elek@gmail.com");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithTooShortTopLevelDomainInEmail_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "teszt-elek@gmail.c");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithEmailStartingWithSpecialCharacter_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "-tesztelek@gmail.com");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }

  @Test void createUser_WithEmailEndingWithSpecialCharacter_ShouldThrowException() {
    InputClientDTO inputClientDTO = new InputClientDTO("Teszt Elek", "tesztelek@gmail.com-");
    Throwable exception = assertThrows (InvalidEmailException.class, () -> clientService.saveClient(inputClientDTO));
    assertEquals("Please give a valid email address!", exception.getMessage());
  }
}