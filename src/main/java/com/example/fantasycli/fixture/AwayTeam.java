package com.example.fantasycli.fixture;

import com.example.fantasycli.team.Team;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "away_teams")
public class AwayTeam {
	@Id
	private int away_id;
	private Boolean winner;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	@OneToOne
	@MapsId
	private Fixture fixture;
	@Override
	public String toString() {
		return "AwayTeam [away_id=" + away_id + ", winner=" + winner + ", team=" + team + "]";
	}
	
	
}
