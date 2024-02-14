package com.example.fantasycli.team;

import com.example.fantasycli.league.League;
import com.example.fantasycli.venue.Venue;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "teams")
public class Team {
	@Id
	private int id;
	@Column(unique = true)
	private String name;
	private String code;
	private Integer founded;
	private String logo;
	@ManyToOne
	private League league;
	@ManyToOne
	private Venue venue;
}
