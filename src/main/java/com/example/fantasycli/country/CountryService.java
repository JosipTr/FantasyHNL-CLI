package com.example.fantasycli.country;

import org.slf4j.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class CountryService extends GlobalService{
	private final CountryRepository countryRepository;
	private final Logger logger = LoggerFactory.getLogger(CountryService.class);
	
	@ShellMethod(key = "save countries")
	public void getCountries() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		
		String body = apiRepository.getCountry();
		
		var bodyJson = gson.fromJson(body, JsonObject.class);
		var responseArray = bodyJson.getAsJsonArray("response");
		
		for (var element : responseArray) {
			var countryJson = element.getAsJsonObject();
			var country = gson.fromJson(countryJson, Country.class);
			
			var savedCountry = countryRepository.save(country);
			
			logger.info(savedCountry.toString());
		}
	}
	
	@ShellMethod(key = "get countries")
	private void lookCountries() {
		var countries = countryRepository.findAll();
		
		for (var country : countries) {
			System.out.println(country);
		}
	}
}
