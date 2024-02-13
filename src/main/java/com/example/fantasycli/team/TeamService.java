package com.example.fantasycli.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.league.LeagueRepository;
import com.example.fantasycli.venue.Venue;
import com.example.fantasycli.venue.VenueRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;

@ShellComponent
@Transactional
public class TeamService extends GlobalService{
	private final TeamRepository teamRepository;
	private final LeagueRepository leagueRepository;
	private final VenueRepository venueRepository;
	private final Logger logger = LoggerFactory.getLogger(TeamService.class);

	public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository,
			VenueRepository venueRepository) {
		super();
		this.teamRepository = teamRepository;
		this.leagueRepository = leagueRepository;
		this.venueRepository = venueRepository;
	}

	@ShellMethod(key = "save teams")
	public void getTeams() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		
		String body = apiRepository.getTeams();

		JsonObject jsonObject = gson.fromJson(body, JsonObject.class);

		JsonArray responseArray = jsonObject.getAsJsonArray("response");
		var league = leagueRepository.getReferenceById(210);
		for (var element : responseArray) {

			JsonObject teamJson = element.getAsJsonObject().get("team").getAsJsonObject();
			Team team = gson.fromJson(teamJson, Team.class);
			int venueId = element.getAsJsonObject().get("venue").getAsJsonObject().get("id").getAsInt();
			Venue venue = venueRepository.getReferenceById(venueId);
			
			team.setLeague(league);
			team.setVenue(venue);

			var savedTeam = teamRepository.save(team);
			logger.info(savedTeam.toString());
		}
		return;
	}
	
	@ShellMethod(key = "get team")
	public void getTea() {
		var g = teamRepository.getReferenceById(620);
		System.out.println(g.toString());
	}
}
