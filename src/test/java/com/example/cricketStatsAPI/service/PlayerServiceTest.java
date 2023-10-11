package com.example.cricketStatsAPI.service;

import com.example.cricketStatsAPI.model.Match;
import com.example.cricketStatsAPI.model.Player;
import com.example.cricketStatsAPI.model.Repository.MatchRepository;
import com.example.cricketStatsAPI.model.Repository.PlayerRepository;
import com.example.cricketStatsAPI.model.Repository.Service.PlayerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
class PlayerServiceTest {
	@Autowired
	private PlayerService playerService;

	@MockBean
	private PlayerRepository playerRepository;

	@MockBean
	private MatchRepository matchRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllPlayers() {
		List<Player> players = Arrays.asList(new Player(1L, "Rohit Sharma", new Date(), 1L, "India"),
				new Player(2L, "Virat Kohli", new Date(), 2L, "India"),
				new Player(3L, "Hardik Pandya", new Date(), 3L, "India"));
		when(playerRepository.findAll()).thenReturn(players);
		List<Player> result = playerService.getAllPlayers();
		assertEquals(3, result.size());
	}

	@Test
	void testCreatePlayer() {
		Player newPlayer = new Player(4L, "MS Dhoni", new Date(), 4L, "India");
		when(playerRepository.save(any(Player.class))).thenReturn(newPlayer);
		Player createdPlayer = playerService.createPlayer(newPlayer);
		assertEquals(newPlayer.getId(), createdPlayer.getId());
		assertEquals(newPlayer.getName(), createdPlayer.getName());
	}

	@Test
	void testUpdatePlayer() {
		Long playerId = 1L;
		Player originalPlayer = new Player(playerId, "Rohit Sharma", new Date(), 1L, "India");
		Player updatedPlayerData = new Player(playerId, "Rohit Sharma (Updated)", new Date(), 1L, "India");

		when(playerRepository.save(any(Player.class))).thenReturn(updatedPlayerData);
		when(playerRepository.findById(playerId)).thenReturn(Optional.of(originalPlayer));
		Player updatedPlayer = playerService.updatePlayer(playerId, updatedPlayerData);
		assertEquals("Rohit Sharma (Updated)", updatedPlayer.getName());
	}

	@Test
	void testDeletePlayer() {
		Long playerId = 1L;
		Player playerToDelete = new Player(playerId, "Rohit Sharma", new Date(), 1L, "India");
		when(playerRepository.findById(playerId)).thenReturn(Optional.of(playerToDelete));
		playerService.deletePlayer(playerId);
		verify(playerRepository, times(1)).deleteById(playerId);
	}

	@Test
	void testGetPlayerById() {
		Long playerId = 1L;
		Player player = new Player(playerId, "Virat Kohli", new Date(), 2L, "India");
		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		Player retrievedPlayer = playerService.getPlayerById(playerId);
		assertEquals(playerId, retrievedPlayer.getId());
	}

	@Test
	void testGetPlayersByCountry() {
		String country = "India";
		List<Player> indianPlayers = Arrays.asList(new Player(1L, "Rohit Sharma", new Date(), 1L, country),
				new Player(2L, "Virat Kohli", new Date(), 2L, country),
				new Player(3L, "Hardik Pandya", new Date(), 3L, country));
		when(playerRepository.findAllByCountry(country)).thenReturn(indianPlayers);
		List<Player> retrievedPlayers = playerService.getPlayersByCountry(country);
		assertEquals(3, retrievedPlayers.size());
	}

	@Test
	void testGetPlayersWithAverageGreaterThan() {
		int average = 50;

		Player player1 = new Player(1L, "Rohit Sharma", new Date(), 1L, "India");
		Player player2 = new Player(2L, "Virat Kohli", new Date(), 2L, "India");
		Player player3 = new Player(3L, "Hardik Pandya", new Date(), 3L, "India");

		when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2, player3));

		Match match1 = new Match(1L, 60, "Stadium 1");
		Match match2 = new Match(2L, 45, "Stadium 2");
		Match match3 = new Match(3L, 70, "Stadium 3");

		when(matchRepository.findAll()).thenReturn(Arrays.asList(match1, match2, match3));

		List<Player> players = playerService.getPlayersWithAverageGreaterThan(average);

		assertEquals(2, players.size());
		assertEquals("Rohit Sharma", players.get(0).getName());
		assertEquals("Hardik Pandya", players.get(1).getName());
	}

	@Test
	public void testGetPlayersSortedByAverageGreaterThan() {
		Player player1 = new Player(1L, "Rohit Sharma", new Date(), 1L, "India");
		Player player2 = new Player(2L, "Virat Kohli", new Date(), 2L, "India");

		Match match1 = new Match(1L, 100, "Stadium A");
		Match match2 = new Match(2L, 75, "Stadium B");

		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);

		List<Match> matches = new ArrayList<>();
		matches.add(match1);
		matches.add(match2);

		Mockito.when(playerRepository.findAll()).thenReturn(players);
		Mockito.when(matchRepository.findAll()).thenReturn(matches);

		List<Player> sortedPlayers = playerService.getPlayersSortedByAverageGreaterThan(50);

		Assert.notEmpty(sortedPlayers, "Sorted player list should not be empty");
		Assert.isTrue(sortedPlayers.size() == 2, "There should be 2 sorted players");
		Assert.isTrue(sortedPlayers.get(0).getName().equals("Rohit Sharma"), "First player should be Rohit Sharma");
		Assert.isTrue(sortedPlayers.get(1).getName().equals("Virat Kohli"), "Second player should be Virat Kohli");
	}
}
