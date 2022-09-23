package com.example.jobsearch;

import com.example.jobsearch.repositories.ClientRepository;
import com.example.jobsearch.repositories.PositionRepository;
import com.example.jobsearch.services.DBPopulaterImpl;
import com.example.jobsearch.services.DBPopulater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class JobsearchApplication implements ApplicationRunner {

  private final ClientRepository clientRepository;
  private final PositionRepository positionRepository;

  @Autowired
  public JobsearchApplication(ClientRepository clientRepository, PositionRepository positionRepository) {
    this.clientRepository = clientRepository;
    this.positionRepository = positionRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(JobsearchApplication.class, args);
  }

  @Bean
  public Docket swaggerConfiguration() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.jobsearch"))
        .build()
        .apiInfo(apiDetails());
  }

  private ApiInfo apiDetails() {
    return new ApiInfoBuilder()
        .title("Job Search API")
        .version("1.0.0")
        .description("upload and/or search jobs")
        .build();
  }

  @Override
  public void run(ApplicationArguments arg0) throws Exception {
    DBPopulater dbPopulate = new DBPopulaterImpl(clientRepository, positionRepository);
    dbPopulate.populateDB();
    System.out.println("Application started");
  }
}