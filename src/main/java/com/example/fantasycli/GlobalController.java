package com.example.fantasycli;

import java.io.IOException;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.country.CountryService;
import com.example.fantasycli.fixture.FixtureService;
import com.example.fantasycli.league.LeagueService;
import com.example.fantasycli.player.PlayerService;
import com.example.fantasycli.team.TeamService;
import com.example.fantasycli.venue.VenueService;

@ShellComponent
public class GlobalController {
	private CountryService countryService;
	private VenueService venueService;
	private LeagueService leagueService;
	private TeamService teamService;
	private PlayerService playerService;
	private FixtureService fixtureService;
	
	public GlobalController(CountryService countryService, VenueService venueService, LeagueService leagueService,
			TeamService teamService, PlayerService playerService, FixtureService fixtureService) {
		super();
		this.countryService = countryService;
		this.venueService = venueService;
		this.leagueService = leagueService;
		this.teamService = teamService;
		this.playerService = playerService;
		this.fixtureService = fixtureService;
	}


	@ShellMethod(key = "update")
	public void update() throws InterruptedException, IOException {
		countryService.getCountries();
		venueService.saveVenues();
		leagueService.saveLeagues();
		teamService.getTeams();
		playerService.getTeamPlayers();
		fixtureService.getFixtures();
		fixtureService.getStatistic();
	}
}
