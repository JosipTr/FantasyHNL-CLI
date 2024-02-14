package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "passes")
public class Pass {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer total;
	private Integer key;
	private String accuracy;
}
