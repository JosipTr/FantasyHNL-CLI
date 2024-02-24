package com.example.fantasycli.fixture.statistic.penalty;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "penalties")
public class Penalty {
	@EmbeddedId
	private StatisticId id;
	private Integer won;
	private Integer commited;
	private Integer scored;
	private Integer missed;
	private Integer saved;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Penalty [id=" + id + ", won=" + won + ", commited=" + commited + ", scored=" + scored + ", missed="
				+ missed + ", saved=" + saved + "]";
	}
	
	
}
