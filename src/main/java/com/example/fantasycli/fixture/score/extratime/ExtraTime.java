package com.example.fantasycli.fixture.score.extratime;

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

	@Override
	public String toString() {
		return "ExtraTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	
	
}
