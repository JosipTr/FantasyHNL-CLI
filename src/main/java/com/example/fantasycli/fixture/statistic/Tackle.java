package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "tackles")
public class Tackle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer total;
	private Integer blocks;
	private Integer intereptions;
}
