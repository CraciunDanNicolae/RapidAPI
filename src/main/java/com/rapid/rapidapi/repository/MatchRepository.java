package com.rapid.rapidapi.repository;
import com.rapid.rapidapi.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends  JpaRepository<Match, Long>{
    List<Match> findByMatchType(String matchType);
    void deleteByMatchType(String matchType);
    boolean existsByDate(LocalDate date);
}
