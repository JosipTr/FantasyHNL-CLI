package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tackles")
public class Tackle {
	@EmbeddedId
	private StatisticId id;
	private Integer total;
	private Integer blocks;
	private Integer intereptions;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Tackle [id=" + id + ", total=" + total + ", blocks=" + blocks + ", intereptions=" + intereptions + "]";
	}
	
}
