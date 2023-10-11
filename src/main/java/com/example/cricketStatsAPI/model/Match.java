package com.example.cricketStatsAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int score;
	private String stadium;
	@ManyToOne
	private Player player;

	public Match(Long id, int score, String stadium) {
		super();
		this.id = id;
		this.score = score;
		this.stadium = stadium;
	}

	public Match() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", score=" + score + ", stadium=" + stadium + "]";
	}

}
