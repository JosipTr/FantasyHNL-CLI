package com.example.fantasycli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Repository;

@Repository
public class FileApiRepositoryImpl implements ApiRepository {
	public FileApiRepositoryImpl() {
		super();
	}

	@Override
	public String getCountry() {

		try {
			String body = Files.readString(Path.of("./src/main/resources/data/countries.json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getLeagues() {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/league.json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTeams() {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/teams.json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getPlayers(int page) {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/players" + page + ".json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getFixtures() {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/fixtures.json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTeamPlayers(int teamId) {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/team_players" + teamId + ".json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getPlayerStatistic(int fixtureId) {
		try {
			String body = Files.readString(Path.of("./src/main/resources/data/fixture" + fixtureId + ".json"));
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
