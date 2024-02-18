package com.example.fantasycli.fixture.hometeam;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.awayteam.AwayTeam;
import com.example.fantasycli.team.Team;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class HomeTeam {
	@Id
	private int home_id;
	private Boolean winner;
	@ManyToOne
	private Team team;
	@OneToOne
	@MapsId
	private Fixture fixture;
	@Override
	public String toString() {
		return "HomeTeam [home_id=" + home_id + ", winner=" + winner + ", team=" + team + "]";
	}
	public HomeTeam setHomeTeam(HomeTeam team) {
		this.winner = team.getWinner();
		this.team = team.getTeam();
		return this;
	}
}
