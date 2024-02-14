package com.example.fantasycli.fixture;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jline.utils.Timeout;
import org.slf4j.*;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.fixture.statistic.Card;
import com.example.fantasycli.fixture.statistic.Dribble;
import com.example.fantasycli.fixture.statistic.Duel;
import com.example.fantasycli.fixture.statistic.Foul;
import com.example.fantasycli.fixture.statistic.Game;
import com.example.fantasycli.fixture.statistic.Goal;
import com.example.fantasycli.fixture.statistic.Pass;
import com.example.fantasycli.fixture.statistic.Penalty;
import com.example.fantasycli.fixture.statistic.Shot;
import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.Tackle;
import com.example.fantasycli.player.Player;
import com.example.fantasycli.player.PlayerRepository;
import com.example.fantasycli.team.TeamRepository;
import com.example.fantasycli.venue.Venue;
import com.example.fantasycli.venue.VenueRepository;
import com.google.gson.*;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@Transactional
@AllArgsConstructor
public class FixtureService extends GlobalService {
	private final FixtureRepository fixtureRepository;
	private final VenueRepository venueRepository;
	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;
	private final Logger logger = LoggerFactory.getLogger(FixtureService.class);

	@ShellMethod(key = "save fixtures")
	public void getFixtures() {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();

		String body = apiRepository.getFixtures();

		JsonObject object = gson.fromJson(body, JsonObject.class);
		JsonArray response = object.getAsJsonArray("response");

		for (var element : response) {

			JsonObject fixtureJson = element.getAsJsonObject().getAsJsonObject("fixture");

			var fixture = gson.fromJson(fixtureJson, Fixture.class);

			var venue = getVenue(fixtureJson);
			var status = getStatus(fixtureJson);
			var homeTeam = getHomeTeam(element);
			var awayTeam = getAwayTeam(element);
			var score = getScore(element);

			fixture.setVenue(venue);
			var savedFixture = fixtureRepository.save(fixture);
			
			
			savedFixture.setStatus(status);
			savedFixture.setHomeTeam(homeTeam);
			savedFixture.setAwayTeam(awayTeam);
			savedFixture.setScore(score);
			
			logger.info(savedFixture.toString());
		}
	}

	private Venue getVenue(JsonObject fixtureJson) {
		var venueJson = fixtureJson.get("venue").getAsJsonObject();
		var idElement = venueJson.get("id");
		if (idElement.isJsonNull())
			return null;
		var venueId = idElement.getAsInt();
		var venue = venueRepository.getReferenceById(venueId);
		return venue;
	}

	private GameStatus getStatus(JsonObject fixtureJson) {
		var statusJson = fixtureJson.get("status").getAsJsonObject();
		var status = super.getGson().fromJson(statusJson, GameStatus.class);
		var longStatus = statusJson.get("long").getAsString();
		var shortStatus = statusJson.get("short").getAsString();
		status.setShortStatus(shortStatus);
		status.setLongStatus(longStatus);
		return status;
	}

	private HomeTeam getHomeTeam(JsonElement element) {
		JsonObject teamsJson = element.getAsJsonObject().getAsJsonObject("teams");
		JsonObject homeTeamJson = teamsJson.getAsJsonObject("home");
		var teamId = homeTeamJson.get("id").getAsInt();
		var team = teamRepository.getReferenceById(teamId);
		HomeTeam homeTeam = super.getGson().fromJson(homeTeamJson, HomeTeam.class);
		homeTeam.setTeam(team);
		return homeTeam;
	}

	private AwayTeam getAwayTeam(JsonElement element) {
		JsonObject teamsJson = element.getAsJsonObject().getAsJsonObject("teams");
		JsonObject awayTeamJson = teamsJson.getAsJsonObject("away");
		var teamId = awayTeamJson.get("id").getAsInt();
		var team = teamRepository.getReferenceById(teamId);
		AwayTeam awayTeam = super.getGson().fromJson(awayTeamJson, AwayTeam.class);
		awayTeam.setTeam(team);
		return awayTeam;
	}

	private Score getScore(JsonElement element) {
		var gson = super.getGson();
		JsonObject scoreJson = element.getAsJsonObject().getAsJsonObject("score");
		JsonObject halfTimeJson = scoreJson.getAsJsonObject("halftime");
		JsonObject fullTimeJson = scoreJson.getAsJsonObject("fulltime");
		JsonObject extraTimeJson = scoreJson.getAsJsonObject("extratime");
		JsonObject penaltyTimeJson = scoreJson.getAsJsonObject("penalty");
		var halfTime = gson.fromJson(halfTimeJson, HalfTime.class);
		var fullTime = gson.fromJson(fullTimeJson, FullTime.class);
		var extraTime = gson.fromJson(extraTimeJson, ExtraTime.class);
		var penaltyTime = gson.fromJson(penaltyTimeJson, PenaltyTime.class);
		var score = gson.fromJson(scoreJson, Score.class);

		score.setExtraTime(extraTime);
		score.setFullTime(fullTime);
		score.setHalfTime(halfTime);
		score.setPenaltyTime(penaltyTime);

		return score;
	}

	@ShellMethod(key = "get statistics")
	public void getStatistic() throws IOException, InterruptedException {
		var apiRepository = super.getApiRepository();
		var gson = super.getGson();

		List<Fixture> fixtures = fixtureRepository.findAll();
		var count = 1;
		var limit = 9;
		for (var fixture : fixtures) {
//			TimeUnit.SECONDS.sleep(5);
			var body = apiRepository.getPlayerStatistic(fixture.getId());
			if (body == null || body.isEmpty()) continue ;

			JsonObject bodyObject = gson.fromJson(body, JsonObject.class);

//			FileWriter writer = new FileWriter("./src/main/resources/data/players" + fixture.getId() + ".json");
//			FileWriter idWriter = new FileWriter("./src/main/resources/data/fixture_ids.txt");
//			var fixtureId = fixture.getId();
//			
//			writer.write(bodyObject.toString());
//			idWriter.write(String.valueOf(fixtureId););
//			writer.close();
//			idWriter.close();

			var responseArray = bodyObject.getAsJsonArray("response");

			for (var element : responseArray) {
				var playersArray = element.getAsJsonObject().getAsJsonArray("players");
				for (var playerElement : playersArray) {
					var pArray = playerElement.getAsJsonObject().getAsJsonArray("players");
					for (var pElement : pArray) {
						var player = getPlayer(pElement);
						var statisticArray = pElement.getAsJsonObject().getAsJsonArray("statistics");
						for (var statElement : statisticArray) {
							if (player == null)
								break;
							var statisticObject = statElement.getAsJsonObject();
							var statistic = gson.fromJson(statisticObject, Statistic.class);
							var game = getGame(statisticObject);
							var shot = getShot(statisticObject);
							var goal = getGoal(statisticObject);
							var pass = getPass(statisticObject);
							var tackle = getTackle(statisticObject);
							var dribble = getDribble(statisticObject);
							var penalty = getPenalty(statisticObject);
							var card = getCard(statisticObject);
							var foul = getFoul(statisticObject);
							var duel = getDuel(statisticObject);
							statistic.setTackle(tackle);
							statistic.setDribble(dribble);
							statistic.setPenalty(penalty);
							statistic.setCard(card);
							statistic.setFoul(foul);
							statistic.setDuel(duel);
							statistic.setPass(pass);
							statistic.setGoal(goal);
							statistic.setGame(game);
							statistic.setShot(shot);
							statistic.setFixture(fixture);
							statistic.setPlayer(player);
							fixture.getStatistic().add(statistic);
							player.getStatistics().add(statistic);

						}
					}
				}
			}	
		}
	}

//	private Fixture getFixture(JsonElement element) {
//		var fixtureObject = element.getAsJsonObject().getAsJsonObject("fixture");
//		var fixtureId = fixtureObject.get("id").getAsInt();
//		var fixture = fixtureRepository.getReferenceById(fixtureId);
//		return fixture;
//	}

	private Player getPlayer(JsonElement element) {
		var playerObject = element.getAsJsonObject().getAsJsonObject("player");
		var playerId = playerObject.get("id").getAsInt();
		var player = playerRepository.findById(playerId);
		if (player.isEmpty())
			return null;
		return player.get();
	}

	private Game getGame(JsonObject statisticObject) {
		var gameObject = statisticObject.getAsJsonObject("games");
		var game = super.getGson().fromJson(gameObject, Game.class);
		return game;
	}

	private Shot getShot(JsonObject statisticObject) {
		var object = statisticObject.getAsJsonObject("shots");
		var shot = super.getGson().fromJson(object, Shot.class);
		var onObject = object.get("on");
		if (onObject.isJsonNull())
			return shot;
		shot.setOn_goal(onObject.getAsInt());
		return shot;
	}

	private Goal getGoal(JsonObject statisticObject) {
		var object = statisticObject.getAsJsonObject("goals");
		var goal = super.getGson().fromJson(object, Goal.class);
		return goal;
	}

	private Pass getPass(JsonObject statisticObject) {
		var object = statisticObject.getAsJsonObject("passes");
		var pass = super.getGson().fromJson(object, Pass.class);
		return pass;
	}

	private Tackle getTackle(JsonObject statisticObject) {
		JsonObject tacklesObject = statisticObject.getAsJsonObject("tackles");
		return super.getGson().fromJson(tacklesObject, Tackle.class);
	}

	private Duel getDuel(JsonObject statisticObject) {
		JsonObject duelsObject = statisticObject.getAsJsonObject("duels");
		return super.getGson().fromJson(duelsObject, Duel.class);
	}

	private Dribble getDribble(JsonObject statisticObject) {
		JsonObject dribblesObject = statisticObject.getAsJsonObject("dribbles");
		return super.getGson().fromJson(dribblesObject, Dribble.class);
	}

	private Foul getFoul(JsonObject statisticObject) {
		JsonObject foulsObject = statisticObject.getAsJsonObject("fouls");
		return super.getGson().fromJson(foulsObject, Foul.class);
	}

	private Card getCard(JsonObject statisticObject) {
		JsonObject cardsObject = statisticObject.getAsJsonObject("cards");
		return super.getGson().fromJson(cardsObject, Card.class);
	}

	private Penalty getPenalty(JsonObject statisticObject) {
		JsonObject penaltyObject = statisticObject.getAsJsonObject("penalty");
		return super.getGson().fromJson(penaltyObject, Penalty.class);
	}

	@ShellMethod(key = "get fixture")
	public void getFixture() {
		var fixture = fixtureRepository.getReferenceById(1034834);
		System.out.println(fixture);
	}
}
