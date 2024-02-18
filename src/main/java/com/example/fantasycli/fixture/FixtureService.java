package com.example.fantasycli.fixture;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jline.utils.Timeout;
import org.slf4j.*;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.fixture.awayteam.AwayTeam;
import com.example.fantasycli.fixture.gamestatus.GameStatus;
import com.example.fantasycli.fixture.hometeam.HomeTeam;
import com.example.fantasycli.fixture.score.Score;
import com.example.fantasycli.fixture.score.extratime.ExtraTime;
import com.example.fantasycli.fixture.score.fulltime.FullTime;
import com.example.fantasycli.fixture.score.halftime.HalfTime;
import com.example.fantasycli.fixture.score.penaltytime.PenaltyTime;
import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.card.Card;
import com.example.fantasycli.fixture.statistic.dribble.Dribble;
import com.example.fantasycli.fixture.statistic.duel.Duel;
import com.example.fantasycli.fixture.statistic.foul.Foul;
import com.example.fantasycli.fixture.statistic.game.Game;
import com.example.fantasycli.fixture.statistic.goal.Goal;
import com.example.fantasycli.fixture.statistic.pass.Pass;
import com.example.fantasycli.fixture.statistic.penalty.Penalty;
import com.example.fantasycli.fixture.statistic.shot.Shot;
import com.example.fantasycli.fixture.statistic.tackle.Tackle;
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
			var score = getScore(element, fixture);
			status.setFixture(fixture);
			homeTeam.setFixture(fixture);
			awayTeam.setFixture(fixture);
			score.setFixture(fixture);

			fixture.setVenue(venue);

			var fix = fixtureRepository.findById(fixture.getId());

			if (fix.isPresent()) {
				
				var fixScore = fix.get().getScore();
				var et = fixScore.getExtraTime().setExtraTime(score.getExtraTime());
				var ft = fixScore.getFullTime().setFullTime(score.getFullTime());
				var ht = fixScore.getHalfTime().setHalfTime(score.getHalfTime());
				var pt = fixScore.getPenaltyTime().setPenaltyTime(score.getPenaltyTime());
				fixScore.setExtraTime(et);
				fixScore.setFullTime(ft);
				fixScore.setPenaltyTime(pt);
				fixScore.setHalfTime(ht);
//				fixScore.setFixture(fix.get());
				var at = fix.get().getAwayTeam().setAwayTeam(awayTeam);
				var homeT = fix.get().getHomeTeam().setHomeTeam(homeTeam);
				var sts = fix.get().getStatus().setGameStatus(status);
				fix.get().setAwayTeam(at);
				fix.get().setStatus(sts);
				fix.get().setHomeTeam(homeT);
				fix.get().setScore(fixScore);
				fix.get().setFixture(fixture);

				logger.info(fix.toString());
			} else {
				var savedFixture = fixtureRepository.save(fixture);

				savedFixture.setStatus(status);
				savedFixture.setHomeTeam(homeTeam);
				savedFixture.setAwayTeam(awayTeam);
				savedFixture.setScore(score);

				logger.info(savedFixture.toString());
			}

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

	private Score getScore(JsonElement element, Fixture fixture) {
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
		score.setFixture(fixture);

		halfTime.setScore(score);
		fullTime.setScore(score);
		extraTime.setScore(score);
		penaltyTime.setScore(score);

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
//		var count = 1;
//		var limit = 9;
//		var total = fixtures.size();
		for (var fixture : fixtures) {

//			try {
//			Files.readString(Path.of("./src/main/resources/data/players" + fixture.getId() + ".json"));
//			System.out.println("Exist");
//			total--;
//			continue;
//			} catch (Exception e) {
//				System.out.println("File does not exist. Continue!");
//			}
//			TimeUnit.SECONDS.sleep(5);
			var body = apiRepository.getPlayerStatistic(fixture.getId());
//			if (body == null || body.isEmpty()) continue ;

			JsonObject bodyObject = gson.fromJson(body, JsonObject.class);
			if (bodyObject == null || bodyObject.isJsonNull() || bodyObject.isEmpty())
				continue;

//			FileWriter writer = new FileWriter("./src/main/resources/data/players" + fixture.getId() + ".json");
//			FileWriter idWriter = new FileWriter("./src/main/resources/data/fixture_ids.txt");
//			var fixtureId = fixture.getId();
//			
//			writer.write(bodyObject.toString());
//			idWriter.write(String.valueOf(fixtureId));
//			writer.close();
//			idWriter.close();

			var responseArray = bodyObject.getAsJsonArray("response");
			if (responseArray.isJsonNull() || responseArray.isEmpty() || responseArray.size() == 0)
				continue;

			for (var element : responseArray) {
				var playersArray = element.getAsJsonObject().getAsJsonArray("players");
				if (playersArray.isJsonNull() || playersArray.isEmpty() || playersArray.size() == 0)
					continue;
				for (var playerElement : playersArray) {
					var pArray = playerElement.getAsJsonObject().getAsJsonArray("players");
					if (pArray.isJsonNull() || pArray.isEmpty() || pArray.size() == 0)
						continue;
					for (var pElement : pArray) {
						var player = getPlayer(pElement);
						var statisticArray = pElement.getAsJsonObject().getAsJsonArray("statistics");
						if (statisticArray.isJsonNull() || statisticArray.isEmpty() || statisticArray.size() == 0)
							continue;
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
							fixture.getStatistics().add(statistic);
							player.getStatistics().add(statistic);

						}
					}
				}
			}
//			var progress = (double) count / total * 100;
//			if (count == limit) {
//				limit *= 2;
//				logger.info("Progress: " + progress + "%");
//				logger.info("Count: " + count);
//				System.out.println("total: " + total);
//				TimeUnit.SECONDS.sleep(65);
//			}

//			
//			logger.info("Progress: " + progress + "%");
//			logger.info("Count: " + count);
//			System.out.println("total: " + total);
//			count++;
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