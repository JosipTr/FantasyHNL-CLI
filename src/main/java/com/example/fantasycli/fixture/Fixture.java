package com.example.fantasycli.fixture;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.example.fantasycli.fixture.awayteam.AwayTeam;
import com.example.fantasycli.fixture.event.Event;
import com.example.fantasycli.fixture.fixturegoals.FixtureGoals;
import com.example.fantasycli.fixture.gamestatus.GameStatus;
import com.example.fantasycli.fixture.hometeam.HomeTeam;
import com.example.fantasycli.fixture.score.Score;
import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.test.Item;
import com.example.fantasycli.venue.Venue;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Setter
@Table(name = "fixtures")
public class Fixture {
	@Id
	private int id;
	private String referee;
	private String timezone;
	private String date;
	private Integer timestamp;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private FixtureGoals fixtureGoals;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Venue venue;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private GameStatus status;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private HomeTeam homeTeam;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private AwayTeam awayTeam;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private Score score;
	@OneToMany(mappedBy = "fixture", cascade = CascadeType.PERSIST)
	private Set<Event> event = new HashSet<>();
	@OneToMany(mappedBy = "fixture", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Statistic> statistics = new HashSet<>();
	
	public void addStatistic(Statistic statistic) {
		statistic.setFixture(this);
//		this.statistics.add(statistic);
		this.getStatistics().add(statistic);
	}
	
	public void removeAllStatistics() {
		Set<Statistic> tmp = new HashSet<>(this.statistics);
		for (Iterator<Statistic> statisticIterator = tmp.iterator(); statisticIterator.hasNext();) {
			Statistic statistic = statisticIterator.next();
//			statistic.removeStatistic();
			statistic.setFixture(null);
			statisticIterator.remove();
		}
//		this.getStatistics().clear();
//		this.setItems(tmp);
		this.getStatistics().addAll(tmp);
	}

	public void setFixture(Fixture fixture) {
		this.referee = fixture.getReferee();
		this.timezone = fixture.getTimezone();
		this.date = fixture.getDate();
		this.timestamp = fixture.getTimestamp();
	}
}
