package com.example.fantasycli.league;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.country.CountryRepository;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class LeagueService extends GlobalService{
	private final LeagueRepository leagueRepository;
	private final CountryRepository countryRepository;
	private final Logger logger = LoggerFactory.getLogger(LeagueService.class);


@ShellMethod(key = "save leagues")
@Transactional
	public void saveLeagues() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		
		String body = apiRepository.getLeagues();

		JsonObject jsonObject = gson.fromJson(body, JsonObject.class);

		var responseArray = jsonObject.getAsJsonArray("response");
		
		for (var element : responseArray) {
			var leagueJson = element.getAsJsonObject().getAsJsonObject("league");
			var countryName = element.getAsJsonObject().getAsJsonObject("country").get("name").getAsString();
			var country = countryRepository.getCountryByName(countryName);
			League league = gson.fromJson(leagueJson, League.class);
			league.setCountry(country);
			var leg = leagueRepository.findById(league.getId());
			
			leg.ifPresentOrElse(l -> l.setLeague(league), () -> leagueRepository.save(league));
		}
	}
}
