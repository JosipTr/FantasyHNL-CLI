package com.example.fantasycli.fixture.statistic.goal;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "goals")
public class Goal {
	@EmbeddedId
	private StatisticId id;
	private Integer total;
	private Integer conceded;
	private Integer assists;
	private Integer saves;
	@OneToOne
	@MapsId
	private Statistic statistic;
	
	@Override
	public String toString() {
		return "Goal [id=" + id + ", total=" + total + ", conceded=" + conceded + ", assists=" + assists + ", saves="
				+ saves + "]";
	}	
	
}