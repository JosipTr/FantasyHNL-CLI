package com.example.fantasycli.venue;

import org.slf4j.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class VenueService extends GlobalService {
	private final VenueRepository venueRepository;
	private final Logger logger = LoggerFactory.getLogger(VenueService.class);

	@ShellMethod(key = "save venues")
	@Transactional
	public void saveVenues() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();

		String body = apiRepository.getTeams();

		JsonObject jsonObject = gson.fromJson(body, JsonObject.class);

		JsonArray responseArray = jsonObject.getAsJsonArray("response");
		for (var element : responseArray) {

			JsonObject venueJson = element.getAsJsonObject().get("venue").getAsJsonObject();

			Venue venue = gson.fromJson(venueJson, Venue.class);

			var ven = venueRepository.findById(venue.getId());

			ven.ifPresentOrElse(t -> t.setVenue(venue), () -> venueRepository.save(venue));

		}
	}
}
