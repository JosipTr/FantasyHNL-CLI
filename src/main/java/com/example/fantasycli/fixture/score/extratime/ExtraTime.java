package com.example.fantasycli.fixture.score.extratime;

import com.example.fantasycli.fixture.score.Score;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "extra_times")
public class ExtraTime {
	@Id
	private int id;
	private Integer home;
	private Integer away;
	@OneToOne
	@MapsId
	private Score score;
	@Override
	public String toString() {
		return "ExtraTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	public ExtraTime setExtraTime(ExtraTime time) {
		this.home = time.getHome();
		this.away = time.getAway();

		return this;
	}
	
}
