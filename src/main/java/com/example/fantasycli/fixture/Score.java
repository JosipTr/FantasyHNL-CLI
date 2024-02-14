package com.example.fantasycli.fixture;

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
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@OneToOne(cascade = CascadeType.PERSIST)
	private HalfTime halfTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private FullTime fullTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private ExtraTime extraTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private PenaltyTime penaltyTime;
}
