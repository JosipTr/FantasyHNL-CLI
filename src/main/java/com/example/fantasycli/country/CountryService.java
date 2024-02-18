package com.example.fantasycli.country;

import org.slf4j.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class CountryService extends GlobalService{
	private final CountryRepository countryRepository;
	private final Logger logger = LoggerFactory.getLogger(CountryService.class);
	
	@ShellMethod(key = "save countries")
	@Transactional
	public void getCountries() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		
		String body = apiRepository.getCountry();
		
		var bodyJson = gson.fromJson(body, JsonObject.class);
		var responseArray = bodyJson.getAsJsonArray("response");
		
		for (var element : responseArray) {
			var countryJson = element.getAsJsonObject();
			var countryGson = gson.fromJson(countryJson, Country.class);
			
			var countryOpt = countryRepository.findByName(countryGson.getName());

			countryOpt.ifPresentOrElse(c -> update(c, countryGson), () -> save(countryGson));
		}
	}
	
	private void update(Country country, Country countryGson) {
		country.setCode(countryGson.getCode());
		country.setFlag(countryGson.getFlag());
	}
	
	private void save(Country countryGson) {
		var savedCountry = countryRepository.save(countryGson);
		logger.info(savedCountry.toString());
	}
	
	@ShellMethod(key = "get countries")
	public void lookCountries() {
		var countries = countryRepository.findAll();
		for (var country : countries) {
			System.out.println(country);
		}
	}
}
