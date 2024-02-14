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
public class PenaltyTime {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Integer home;
	private Integer away;
}
