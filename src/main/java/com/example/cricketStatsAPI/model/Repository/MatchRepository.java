package com.example.cricketStatsAPI.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cricketStatsAPI.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
