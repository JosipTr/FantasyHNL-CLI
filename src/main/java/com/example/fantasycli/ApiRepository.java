package com.example.fantasycli;

public interface ApiRepository {

	String getCountry();

	String getLeagues();

	String getTeams();

	String getPlayers(int page);

	String getTeamPlayers(int teamId);
	
	String getFixture(int fixtureId);

	String getFixtures();

	String getPlayerStatistic(int fixtureId);

}
