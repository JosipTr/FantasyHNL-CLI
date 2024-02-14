package com.example.fantasycli.fixture.statistic;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.player.Player;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer offsides;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Game game;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Shot shot;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Pass pass;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Tackle tackle;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Duel duel;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Dribble dribble;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Foul foul;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Card card;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Penalty penalty;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Goal goal;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Player player;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Fixture fixture;
}
