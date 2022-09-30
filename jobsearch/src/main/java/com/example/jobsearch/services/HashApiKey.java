package com.example.jobsearch.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashApiKey {

  String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

  boolean validateApiKey(String originalPassword, String storedPassword)
      throws NoSuchAlgorithmException, InvalidKeySpecException;

}
