package com.example.cricketStatsAPI.model.Repository.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cricketStatsAPI.model.Match;
import com.example.cricketStatsAPI.model.Player;
import com.example.cricketStatsAPI.model.Repository.MatchRepository;
import com.example.cricketStatsAPI.model.Repository.PlayerRepository;
import java.util.stream.Collectors;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final MatchRepository matchRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository, MatchRepository matchRepository) {
		this.playerRepository = playerRepository;
		this.matchRepository = matchRepository;
	}

	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	public Player createPlayer(Player player) {
		return playerRepository.save(player);
	}

	public Player updatePlayer(Long id, Player updatedPlayer) {
		return updatedPlayer;
	}

	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	public Player getPlayerById(Long id) {
		Optional<Player> player = playerRepository.findById(id);
		return player.orElse(null);
	}

	public List<Player> getPlayersByCountry(String country) {
		return playerRepository.findAllByCountry(country);
	}

	public List<Player> getPlayersWithAverageGreaterThan(int average) {
		List<Player> allPlayers = playerRepository.findAll();
		return allPlayers.stream().filter(player -> calculateAverageScore(player) > average)
				.collect(Collectors.toList());
	}

	public double calculateAverageScore(Player player) {
		List<Match> allMatches = matchRepository.findAll();

		List<Match> playerMatches = allMatches.stream().filter(match -> match.getId().equals(player.getMatchId()))
				.collect(Collectors.toList());

		if (playerMatches.isEmpty()) {
			return 0.0;
		}

		int totalScore = 0;
		for (Match match : playerMatches) {
			totalScore += match.getScore();
		}

		double average = (double) totalScore / playerMatches.size();
		return average;
	}

	public List<Player> getPlayersSortedByAverageGreaterThan(int average) {
		List<Player> allPlayers = playerRepository.findAll();

		return allPlayers.stream().filter(player -> calculateAverageScore(player) > average)
				.sorted(Comparator.comparing(Player::getDateOfBirth)) 
				.collect(Collectors.toList());
	}

	
}
