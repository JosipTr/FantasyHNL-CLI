package com.example.fantasycli.fixture;

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
	@JoinColumn(name = "team_id")
	private Team team;
	@OneToOne
	@MapsId
	private Fixture fixture;
	@Override
	public String toString() {
		return "HomeTeam [home_id=" + home_id + ", winner=" + winner + ", team=" + team + "]";
	}
	
}
