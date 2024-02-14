package com.example.fantasycli.fixture;

import jakarta.persistence.*;

@Entity
public class FixtureGoals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Integer home;
	private Integer away;
	
	public FixtureGoals() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FixtureGoals [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
}
