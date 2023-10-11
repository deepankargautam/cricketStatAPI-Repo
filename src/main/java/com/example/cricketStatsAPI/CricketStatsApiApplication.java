package com.example.cricketStatsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import security.SecurityConfiguration;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.cricketStatsAPI.model.Repository")
@EnableWebSecurity
@Import(SecurityConfiguration.class)
public class CricketStatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketStatsApiApplication.class, args);
	}

}
