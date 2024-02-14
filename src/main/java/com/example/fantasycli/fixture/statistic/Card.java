package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "cards")
public class Card {
	@EmbeddedId
	private StatisticId id;
	private Integer yellow;
	private Integer red;
	@OneToOne
	@MapsId
	private Statistic statistic;
	
	@Override
	public String toString() {
		return "Card [id=" + id + ", yellow=" + yellow + ", red=" + red + "]";
	}
	
	
}
