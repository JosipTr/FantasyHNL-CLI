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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private EventTime time;
	@ManyToOne
	private Team team;
	@ManyToOne
	private Player player;
	@ManyToOne
	private Player assist;
	private String type;
	private String detail;
	private String comments;
	@ManyToOne
	private Fixture fixture;
	@Override
	public String toString() {
		return "Event [id=" + id + ", assist=" + assist + ", type=" + type + ", detail=" + detail + ", comment="
				+ comments + "]";
	}
	
	
}
