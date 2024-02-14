package com.example.fantasycli.fixture;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "full_times")
public class FullTime {
	@Id
	private int id;
	private Integer home;
	private Integer away;
	@OneToOne
	@MapsId
	private Score score;
	@Override
	public String toString() {
		return "FullTime [id=" + id + ", home=" + home + ", away=" + away + "]";
	}
	
	
}
