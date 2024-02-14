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
@ToString
@EqualsAndHashCode
public class HomeTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int home_id;
	private Boolean winner;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
}
