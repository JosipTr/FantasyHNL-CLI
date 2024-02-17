package com.example.fantasycli.fixture.score.penaltytime;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter 
@EqualsAndHashCode
public class PenaltyTime {
	@Id
	private int id;
	private Integer home;
	private Integer away;

	@Override
	public String toString() {
		return "PenaltyTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	
	
}
