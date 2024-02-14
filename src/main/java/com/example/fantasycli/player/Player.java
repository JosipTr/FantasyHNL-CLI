package com.example.fantasycli.player;

import java.util.HashSet;
import java.util.Set;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.player.birth.Birth;
import com.example.fantasycli.team.Team;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "players")
public class Player {
	@Id
	private int id;
	private String name;
	private Integer age;
	private Integer number;
	private String position;
	private String firstname;
	private String lastname;
	private String nationality;
	private String height;
	private String weight;
	private Boolean injured;
	private String photo;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Birth birth;
	@ManyToOne
	private Team team;
	@OneToMany(mappedBy = "player", cascade = CascadeType.PERSIST)
	private Set<Statistic> statistics = new HashSet<>();
}
