package com.example.fantasycli.fixture;

import jakarta.persistence.*;

@Entity
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
	
	
}
