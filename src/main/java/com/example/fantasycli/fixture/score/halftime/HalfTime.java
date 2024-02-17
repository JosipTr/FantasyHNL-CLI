package com.example.fantasycli.fixture.score.halftime;

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

	@Override
	public String toString() {
		return "HalfTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}

}