package com.example.fantasycli.fixture.score.halftime;

import com.example.fantasycli.fixture.score.Score;
import com.example.fantasycli.fixture.score.extratime.ExtraTime;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "half_times")
public class HalfTime {
	@Id
	private int id;
	private Integer home;
	private Integer away;
	@OneToOne
	@MapsId
	private Score score;
	@Override
	public String toString() {
		return "HalfTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	public HalfTime setHalfTime(HalfTime time) {
		this.home = time.getHome();
		this.away = time.getAway();

		return this;
	}
}
