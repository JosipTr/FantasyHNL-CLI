package com.example.fantasycli.fixture.gamestatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "status")
public class GameStatus {
	@Id
	private int id;
	private String longStatus;
	private String shortStatus;
	private Integer elapsed;

	@Override
	public String toString() {
		return "GameStatus [id=" + id + ", longStatus=" + longStatus + ", shortStatus=" + shortStatus + ", elapsed="
				+ elapsed;
	}
	
	
}


