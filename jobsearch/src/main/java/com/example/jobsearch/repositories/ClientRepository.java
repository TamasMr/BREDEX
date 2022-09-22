package com.example.jobsearch.repositories;

import com.example.jobsearch.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  boolean existsByApiKey(String apikey);

  boolean existsByEmail(String email);
}