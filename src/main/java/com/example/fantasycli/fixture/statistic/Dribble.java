package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "dribbles")
public class Dribble {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer attempts;
	private Integer success;
	private Integer past;
}
