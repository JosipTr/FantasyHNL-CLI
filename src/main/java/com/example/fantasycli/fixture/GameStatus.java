package com.example.fantasycli.fixture;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "status")
public class GameStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String longStatus;
	private String shortStatus;
	private Integer elapsed;
}


