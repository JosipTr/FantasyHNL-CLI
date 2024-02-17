package com.example.fantasycli.fixture.fixturegoals;

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

	@Override
	public String toString() {
		return "FixtureGoals [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	
	
}
