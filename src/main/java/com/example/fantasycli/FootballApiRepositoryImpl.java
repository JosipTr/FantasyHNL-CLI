package com.example.fantasycli;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

//@Repository
public class FootballApiRepositoryImpl implements ApiRepository {

	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	private final HttpEntity<Object> httpEntity;

	public FootballApiRepositoryImpl(@Value("${spring.security.api-key}") String apiKey,
			@Value("${spring.security.api-header}") String apiHeader) {
		super();
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		this.httpEntity = new HttpEntity<Object>(this.headers);
		this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		this.headers.add(apiHeader, apiKey);
	}

	@Override
	public String getCountry() {
		var uri = "https://v3.football.api-sports.io/countries";
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getLeagues() {
		var uri = "https://v3.football.api-sports.io/leagues?country=Croatia&season=2023";
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getTeams() {
		var uri = "https://v3.football.api-sports.io/teams?league=210&season=2023";
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getPlayers(int page) {
		var uri = "https://v3.football.api-sports.io/players?league=210&season=2023&page=" + page;
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getTeamPlayers(int teamId) {
		var uri = "https://v3.football.api-sports.io/players/squads?team=" + teamId;
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}
	
	@Override
	public String getFixture(int fixtureId) {
		var uri = "https://v3.football.api-sports.io/fixtures?id=" + fixtureId;
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getFixtures() {
		var uri = "https://v3.football.api-sports.io/fixtures?league=210&season=2023";
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

	@Override
	public String getPlayerStatistic(int fixtureId) {
		var uri = "https://v3.football.api-sports.io/fixtures?id=" + fixtureId;
		var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		return response.getBody();
	}

}
