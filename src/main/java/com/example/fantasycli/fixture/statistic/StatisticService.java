package com.example.fantasycli.fixture.statistic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.fantasycli.GlobalService;
import com.example.fantasycli.fixture.Fixture;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticService extends GlobalService{
	private final PlayerRepository playerRepository;
	private final EntityManager entityManager;

	public void getStatistics(JsonArray firstPlayerArray, Fixture fixture) {
		var gson = super.getGson();
		if (firstPlayerArray.isJsonNull() || firstPlayerArray.isEmpty())
			return;
		
		for (var firstPlayerElement : firstPlayerArray) {
			var playersArray = firstPlayerElement.getAsJsonObject().getAsJsonArray("players");
			if (playersArray.isJsonNull() || playersArray.isEmpty()) return;
			for (var element : playersArray) {
				var player = getPlayer(element);
				if (player == null) continue;
				var statisticArray = element.getAsJsonObject().getAsJsonArray("statistics");
				
				for (var statisticElement : statisticArray) {
					var statisticObject = statisticElement.getAsJsonObject();
					
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

					fixture.addStatistic(statistic);
					player.addStatistic(statistic);
				}
			}
		}
	}
	
	public void updateStatistics(JsonArray firstPlayerArray, Fixture fixture) throws InterruptedException {
		var gson = super.getGson();
		if (firstPlayerArray.isJsonNull() || firstPlayerArray.isEmpty())
			return;
		
		for (var firstPlayerElement : firstPlayerArray) {
			var playersArray = firstPlayerElement.getAsJsonObject().getAsJsonArray("players");
			if (playersArray.isJsonNull() || playersArray.isEmpty()) return;
			for (var element : playersArray) {
				var player = getPlayer(element);
				if (player == null) continue;
				player.removeAllStatistics();
				fixture.removeAllStatistics();
				var statisticArray = element.getAsJsonObject().getAsJsonArray("statistics");
				
				for (var statisticElement : statisticArray) {
					var statisticObject = statisticElement.getAsJsonObject();
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
//					statistic.setFixture(fixture);
//					statistic.setPlayer(player);
//					statisticSet.add(statistic);
					
//					fixture.addStatistic(statistic);
//					player.addStatistic(statistic);
//					fixture.getStatistics().add(savedStatistic);
//					player.getStatistics().add(savedStatistic);
				}
//				player.updateStatistics(statisticSet);
			}
		}
		entityManager.flush();
//		p.updateStatistics(statisticSet);
//		fixture.updateStatistics(statisticSet);
//		return statisticSet;
	}
	
	
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
}
