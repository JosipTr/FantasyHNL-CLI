package com.example.fantasycli.fixture.score.penaltytime;

import com.example.fantasycli.fixture.score.Score;
import com.example.fantasycli.fixture.score.extratime.ExtraTime;

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
	@OneToOne
	@MapsId
	private Score score;
	@Override
	public String toString() {
		return "PenaltyTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	public PenaltyTime setPenaltyTime(PenaltyTime time) {
		this.home = time.getHome();
		this.away = time.getAway();

		return this;
	}
	
}
