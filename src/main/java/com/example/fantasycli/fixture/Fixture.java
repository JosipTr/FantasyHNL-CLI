package com.example.fantasycli.fixture;

import java.util.HashSet;
import java.util.Set;

import com.example.fantasycli.fixture.awayteam.AwayTeam;
import com.example.fantasycli.fixture.event.Event;
import com.example.fantasycli.fixture.fixturegoals.FixtureGoals;
import com.example.fantasycli.fixture.gamestatus.GameStatus;
import com.example.fantasycli.fixture.hometeam.HomeTeam;
import com.example.fantasycli.fixture.score.Score;
import com.example.fantasycli.fixture.statistic.Statistic;
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
	@OneToOne
	private FixtureGoals fixtureGoals;
	@ManyToOne
	private Venue venue;
	@OneToOne
	private GameStatus status;
	@OneToOne
	private HomeTeam homeTeam;
	@OneToOne
	private AwayTeam awayTeam;
	@OneToOne
	private Score score;
	@OneToMany(mappedBy = "fixture", cascade = CascadeType.PERSIST)
	private Set<Event> event = new HashSet<>();
	@OneToMany(mappedBy = "fixture", cascade = {CascadeType.PERSIST})
	private Set<Statistic> statistics = new HashSet<>();
	
	
	public void setStat(Statistic statistic) {
		this.statistics.add(statistic);
	}
	}
