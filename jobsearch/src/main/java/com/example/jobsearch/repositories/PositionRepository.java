package com.example.jobsearch.repositories;

import com.example.jobsearch.models.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PositionRepository extends JpaRepository<Position, Long> {

  List<Position> findAllByLocation(String Location);

  @Query("SELECT p FROM Position p WHERE p.jobPosition LIKE CONCAT('%', :keyWord, '%')")
  List<Position> findAllByKeyWord(String keyWord);

  @Query("SELECT p FROM Position p WHERE p.location = :location AND p.jobPosition LIKE CONCAT('%', :keyWord, '%')")
  List<Position> findAllByLocationAndKeyWord(String location, String keyWord);
}