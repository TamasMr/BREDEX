package com.example.jobsearch.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public Docket configureSwagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.jobsearch"))
        .build()
        .apiInfo(setApiDetails());
  }

  private ApiInfo setApiDetails() {
    return new ApiInfoBuilder()
        .title("Job Search API")
        .version("1.0.0")
        .description("upload and/or search jobs")
        .build();
  }
}