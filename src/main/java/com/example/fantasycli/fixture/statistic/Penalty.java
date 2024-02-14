package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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
