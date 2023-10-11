package com.example.cricketStatsAPI.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cricketStatsAPI.model.Player;
import com.example.cricketStatsAPI.model.Repository.Service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
	private final PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<Player> getAllPlayers() {
		List<Player> players = playerService.getAllPlayers();
		return players;
	}

	// 1. Create an API to insert players detail.
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public Player createPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}

	// 2. Update Player details
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public Player updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
		return playerService.updatePlayer(id, updatedPlayer);
	}

	// 3. Delete Player details
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deletePlayer(@PathVariable Long id) {
		playerService.deletePlayer(id);
	}

	// 1. Get player detail by passing the player ID
	@GetMapping("/{id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
		Player player = playerService.getPlayerById(id);
		if (player != null) {
			return new ResponseEntity<>(player, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// 2. Get the player list with an average score more than X. Where X is a
	// number.
	@GetMapping("/getPlayersWithAverageGreaterThan/{average}")
	public List<Player> getPlayersWithAverageGreaterThan(@PathVariable int average) {
		return playerService.getPlayersWithAverageGreaterThan(average);
	}

	// 3. Get Player List By country.
	@GetMapping("/country/{country}")
	public List<Player> getPlayersByCountry(@PathVariable String country) {
		return playerService.getPlayersByCountry(country);
	}

	// 4. Get a List of players in sorted order whose average scores are more than
	// Y. where Y is number.
	@GetMapping("/getPlayersSortedByAverageGreaterThan/{average}")
	public List<Player> getPlayersSortedByAverageGreaterThan(@PathVariable int average) {
		return playerService.getPlayersSortedByAverageGreaterThan(average);
	}

}
