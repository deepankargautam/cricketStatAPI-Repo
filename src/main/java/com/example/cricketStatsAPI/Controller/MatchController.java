package com.example.cricketStatsAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.cricketStatsAPI.model.Match;
import com.example.cricketStatsAPI.model.Repository.Service.MatchService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {
	private final MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping
	public List<Match> getAllMatches() {
		return matchService.getAllMatches();
	}

	@GetMapping("/{id}")
	public Optional<Match> getMatchById(@PathVariable Long id) {
		return matchService.getMatchById(id);
	}

	@PostMapping
	public Match createMatch(@RequestBody Match match) {
		return matchService.createMatch(match);
	}

	@PutMapping("/{id}")
	public Match updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
		return matchService.updateMatch(id, updatedMatch);
	}

	@DeleteMapping("/{id}")
	public void deleteMatch(@PathVariable Long id) {
		matchService.deleteMatch(id);
	}
}
