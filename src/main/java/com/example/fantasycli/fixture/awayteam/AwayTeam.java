package com.example.fantasycli.fixture.awayteam;

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
	@Column(name = "id")
	private int away_id;
	private Boolean winner;
	@ManyToOne
	private Team team;

	@Override
	public String toString() {
		return "AwayTeam [away_id=" + away_id + ", winner=" + winner + ", team=" + team + "]";
	}
	
	
}
