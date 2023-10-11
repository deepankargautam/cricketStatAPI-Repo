package com.example.cricketStatsAPI.model.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cricketStatsAPI.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findAllByCountry(String country);
}
