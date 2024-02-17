package com.example.fantasycli.fixture.event;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.event.time.EventTime;
import com.example.fantasycli.player.Player;
import com.example.fantasycli.team.Team;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Event {
	@Id
	private int id;
	@OneToOne
	private EventTime time;
	@OneToOne
	private Team team;
	@OneToOne
	private Player player;
	@OneToOne
	private Player assist;
	private String type;
	private String detail;
	private String comment;
	@ManyToOne
	private Fixture fixture;
}
