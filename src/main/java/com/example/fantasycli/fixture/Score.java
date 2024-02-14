package com.example.fantasycli.fixture;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Score {
	@Id
	private int id;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "score")
	private HalfTime halfTime;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "score")
	private FullTime fullTime;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "score")
	private ExtraTime extraTime;
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "score")
	private PenaltyTime penaltyTime;
	@OneToOne
	@MapsId
	private Fixture fixture;
	@Override
	public String toString() {
		return "Score [id=" + id + ", halfTime=" + halfTime + ", fullTime=" + fullTime + ", extraTime=" + extraTime
				+ ", penaltyTime=" + penaltyTime + "]";
	}
	
}
