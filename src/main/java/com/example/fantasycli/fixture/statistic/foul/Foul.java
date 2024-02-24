package com.example.fantasycli.fixture.statistic.foul;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "fouls")
public class Foul {
	@EmbeddedId
	private StatisticId id;
	private Integer drawn;
	private Integer committed;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Foul [id=" + id + ", drawn=" + drawn + ", committed=" + committed + "]";
	}
	
	
}
