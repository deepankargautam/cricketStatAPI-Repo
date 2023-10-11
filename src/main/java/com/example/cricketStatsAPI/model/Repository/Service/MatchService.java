package com.example.cricketStatsAPI.model.Repository.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.cricketStatsAPI.model.Match;
import com.example.cricketStatsAPI.model.Repository.MatchRepository;

@Service
public class MatchService {
	private final MatchRepository matchRepository;

	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	public Optional<Match> getMatchById(Long id) {
		return matchRepository.findById(id);
	}

	public Match createMatch(Match match) {
		return matchRepository.save(match);
	}

	public Match updateMatch(Long id, Match updatedMatch) {
		return updatedMatch;
	}

	public void deleteMatch(Long id) {
		matchRepository.deleteById(id);
	}
}
