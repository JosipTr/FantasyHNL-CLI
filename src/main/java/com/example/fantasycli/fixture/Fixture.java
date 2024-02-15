package com.example.fantasycli.fixture;

import java.util.HashSet;
import java.util.Set;

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
	@ManyToOne
	private Venue venue;
	@OneToOne(cascade = CascadeType.PERSIST,mappedBy = "fixture")
	private GameStatus status;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private HomeTeam homeTeam;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private AwayTeam awayTeam;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "fixture")
	private Score score;
	@OneToMany(mappedBy = "fixture", cascade = {CascadeType.PERSIST})
	private Set<Statistic> statistics = new HashSet<>();
	
	
	public void setStat(Statistic statistic) {
		this.statistics.add(statistic);
	}
	}
