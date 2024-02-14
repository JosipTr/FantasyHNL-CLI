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
	
	
}
