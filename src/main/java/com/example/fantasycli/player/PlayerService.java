package com.example.fantasycli.player;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.country.CountryRepository;
import com.example.fantasycli.player.birth.Birth;
import com.example.fantasycli.team.Team;
import com.example.fantasycli.team.TeamRepository;
import com.google.gson.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@Transactional
@AllArgsConstructor
public class PlayerService extends GlobalService {
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final CountryRepository countryRepository;
	private final Logger logger = LoggerFactory.getLogger(PlayerService.class);

	@ShellMethod(key = "save players")
	public void getTeamPlayers() throws InterruptedException, IOException {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		var count = 1;
		List<Team> teams = teamRepository.findAll();
		var total = teams.size();
		for (var team : teams) {
//			 TimeUnit.SECONDS.sleep(2);
//			 if (count == 9) TimeUnit.SECONDS.sleep(62);
			String body = apiRepository.getTeamPlayers(team.getId());

			JsonObject bodyObject = gson.fromJson(body, JsonObject.class);
//			 FileWriter writer = new FileWriter("./src/main/resources/data/team_players" + team.getId() + ".json");
//			 writer.write(bodyObject.toString());

			JsonArray responseArray = bodyObject.getAsJsonArray("response");
			JsonArray playersArray = responseArray.get(0).getAsJsonObject().getAsJsonArray("players");

			for (var element : playersArray) {
				var playerObject = element.getAsJsonObject();
				var player = gson.fromJson(playerObject, Player.class);
				player.setTeam(team);
				var savedPlayer = playerRepository.save(player);
				logger.info(savedPlayer.toString());
			}
//			 writer.close();
			count++;
			var progress = (double) count / total * 100;
			logger.info("Progress: " + progress + "%");
		}
		getPlayers();
		return;
	}

	private void getPlayers() throws InterruptedException, IOException {
		List<Player> players = playerRepository.findAll();
		int limit = 9;
		int page = 1;
		players = playerRecursion(players, page, limit);
		for (var player : players) {
			logger.info(player.toString());
		}
		return;
	}

	private List<Player> playerRecursion(List<Player> players, int page, int limit)
			throws InterruptedException, IOException {
//		TimeUnit.SECONDS.sleep(10);
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();
		String body = apiRepository.getPlayers(page);

		JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
//		FileWriter writer = new FileWriter("./src/main/resources/data/players" + page + ".json");
//		writer.write(jsonObject.toString());

		var total = jsonObject.get("paging").getAsJsonObject().get("total").getAsInt();

		JsonArray responseArray = jsonObject.getAsJsonArray("response");

		for (var element : responseArray) {

			JsonObject playerObject = element.getAsJsonObject().getAsJsonObject("player");
			JsonObject birthObject = playerObject.getAsJsonObject("birth");
			String countryName = birthObject.get("country").getAsString();
			
			int playerId = playerObject.get("id").getAsInt();
			Player playerNew = gson.fromJson(playerObject, Player.class);
			

			

			for (var player : players) {
				if (player.getId() == playerId) {
					Birth birth = gson.fromJson(birthObject, Birth.class);
					var country = countryRepository.getCountryByName(countryName);
					birth.setBirth_country(country);
					player.setFirstname(playerNew.getFirstname());
					player.setHeight(playerNew.getHeight());
					player.setInjured(playerNew.getInjured());
					player.setLastname(playerNew.getLastname());
					player.setName(playerNew.getName());
					player.setNationality(playerNew.getNationality());
					player.setWeight(playerNew.getWeight());
					player.setBirth(birth);
					playerRepository.save(player);
					break;
				}
			}

		}
		double progress = (double) page / total * 100;
		System.out.println("Progress: " + progress + "%");

		if (page == total) {
//			writer.close();
			return players;
		}
		page++;

//		if (page == limit) {
//			TimeUnit.SECONDS.sleep(65);
//			limit *= 2;
//		}
//		writer.close();
		return playerRecursion(players, page, limit);
	}
	
	@ShellMethod(key = "show player")
	public void showPlayer() {
		var player = playerRepository.getReferenceById(1311);
		System.out.println(player);
		System.out.println(player.getStatistics());
//		for (var stat : player.getStatistics()) {
//			System.out.println(stat);
//		}
	}
}
