package com.example.fantasycli.fixture.statistic.pass;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "passes")
public class Pass {
	@EmbeddedId
	private StatisticId id;
	private Integer total;
	private Integer key;
	private String accuracy;
	@OneToOne
	@MapsId
	private Statistic statistic;
	@Override
	public String toString() {
		return "Pass [id=" + id + ", total=" + total + ", key=" + key + ", accuracy=" + accuracy + "]";
	}
	
	
}
