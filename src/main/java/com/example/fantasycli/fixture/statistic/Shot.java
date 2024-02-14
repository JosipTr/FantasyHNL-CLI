package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "shots")
public class Shot {
	@EmbeddedId
	private StatisticId id;
	private Integer total;
	private Integer on_goal;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Shot [id=" + id + ", total=" + total + ", on_goal=" + on_goal + "]";
	}
	
	
}
