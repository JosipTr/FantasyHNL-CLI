package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "penalties")
public class Penalty {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer won;
	private Integer commited;
	private Integer scored;
	private Integer missed;
	private Integer saved;
}
