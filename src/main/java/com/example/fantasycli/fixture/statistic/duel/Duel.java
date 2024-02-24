package com.example.fantasycli.fixture.statistic.duel;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "duels")
public class Duel {
	@EmbeddedId
	private StatisticId id;
	private Integer total;
	private Integer won;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Duel [id=" + id + ", total=" + total + ", won=" + won + "]";
	}
	
}
