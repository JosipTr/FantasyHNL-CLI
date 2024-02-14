package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "games")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer minutes;
	private Integer number;
	private String position;
	private String rating;
	private Boolean captain;
	private Boolean substitute;
}
