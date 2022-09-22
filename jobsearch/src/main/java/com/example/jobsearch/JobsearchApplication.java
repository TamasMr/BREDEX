package com.example.jobsearch;

import com.example.jobsearch.models.Client;
import com.example.jobsearch.models.Position;
import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.repositories.PositionRepository;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class JobsearchApplication implements ApplicationRunner {

  private final ClientRepository clientRepository;
  private final PositionRepository positionRepository;

  public JobsearchApplication(ClientRepository clientRepository, PositionRepository positionRepository) {
    this.clientRepository = clientRepository;
    this.positionRepository = positionRepository;
  }

  @Autowired
  public static void main(String[] args) {
    SpringApplication.run(JobsearchApplication.class, args);
  }
  
  @Override
  public void run(ApplicationArguments arg0) throws Exception {
    clientRepository.save(new Client("Sarah Parker", "sarah-parker.work@gmail.cam", "eb9982ff-ef5f-4218-994a-2dacdf9cad08"));
    clientRepository.save(new Client("Edward Parker", "edward-parker.work@gmail.cam", "b8f7d459-5033-47d2-880f-0fedd959ceb5"));
    clientRepository.save(new Client("Blake Johnson", "blake-johnson.home@gmail.can", "70357d90-c858-4b03-b0e2-05e66329f0ce"));
    clientRepository.save(new Client("Gregory Karlberg", "gregory-karlberg.work@gmail.con",
        "7ee1bb79-9440-4466-aad8-3ac2d5267f91"));
    clientRepository.save(new Client("Adam Sikorsky", "adam-sikorsky.work@gmail.con", "2c44bf66-073d-4f70-a002-9d55b8c04eee"));

    positionRepository.save(
        new Position("junior java developer", "budapest", new URL("https://whatever.com/budapest-junior_java_developer-1001")));
    positionRepository.save(new Position("senior hr manager", "houston", new URL("https://whatever.com/houston-senior_hr_manager-1002")));
    positionRepository.save(new Position("junior astronaut", "houston", new URL("https://whatever.com/houston-junior_astronaut-1003")));
    positionRepository.save(
        new Position("senior ufo spotter", "hannover", new URL("https://whatever.com/hannover-senior_ufo_spotter-1004")));
    positionRepository.save(new Position("java dev", "braunschweig", new URL("https://whatever.com/braunschweig-java_dev-1005")));
    System.out.println("Application started");
  }
}
