package com.example.fantasycli.fixture.statistic.game;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game {
	@EmbeddedId
	private StatisticId id;
	private Integer minutes;
	private Integer number;
	private String position;
	private String rating;
	private Boolean captain;
	private Boolean substitute;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Game [id=" + id + ", minutes=" + minutes + ", number=" + number + ", position=" + position + ", rating="
				+ rating + ", captain=" + captain + ", substitute=" + substitute + "]";
	}
	
	
}
