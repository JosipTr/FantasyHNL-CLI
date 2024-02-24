package com.example.fantasycli.fixture.statistic;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@ToString
@Setter
@EqualsAndHashCode
public class StatisticId {
	private int playerId;
	private int fixtureId;
}
