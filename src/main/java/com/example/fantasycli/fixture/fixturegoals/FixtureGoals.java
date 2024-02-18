package com.example.fantasycli.fixture.fixturegoals;

import com.example.fantasycli.fixture.Fixture;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class FixtureGoals {
	@Id
	private int id;
	private Integer home;
	private Integer away;
	@OneToOne
	@MapsId
	private Fixture fixture;

	@Override
	public String toString() {
		return "FixtureGoals [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	
	public void setFixtureGoals(FixtureGoals goals) {
		this.home = goals.getHome();
		this.away = goals.getAway();
	}
}
