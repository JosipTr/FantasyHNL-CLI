package com.example.fantasycli.fixture;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

}
