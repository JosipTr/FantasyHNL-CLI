package com.example.fantasycli.fixture;

import java.util.HashSet;
import java.util.Set;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.venue.Venue;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "fixtures")
public class Fixture {
	@Id
	private int id;
	private String referee;
	private String timezone;
	private String date;
	private Integer timestamp;
	@ManyToOne
	private Venue venue;
	@OneToOne(cascade = CascadeType.PERSIST)
	private GameStatus status;
	@OneToOne(cascade = CascadeType.PERSIST)
	private HomeTeam homeTeam;
	@OneToOne(cascade = CascadeType.PERSIST)
	private AwayTeam awayTeam;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Score score;
	@OneToMany(mappedBy = "fixture", cascade = {CascadeType.PERSIST})
	private Set<Statistic> statistic = new HashSet<>();
	}
