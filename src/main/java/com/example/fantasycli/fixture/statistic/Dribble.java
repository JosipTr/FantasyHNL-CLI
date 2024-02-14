package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "dribbles")
public class Dribble {
	@EmbeddedId
	private StatisticId id;
	private Integer attempts;
	private Integer success;
	private Integer past;
	@OneToOne
	@MapsId
	private Statistic statistic;
	
	@Override
	public String toString() {
		return "Dribble [id=" + id + ", attempts=" + attempts + ", success=" + success + ", past=" + past + "]";
	}
	
}
