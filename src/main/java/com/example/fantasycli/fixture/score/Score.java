package com.example.fantasycli.fixture.score;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.score.extratime.ExtraTime;
import com.example.fantasycli.fixture.score.fulltime.FullTime;
import com.example.fantasycli.fixture.score.halftime.HalfTime;
import com.example.fantasycli.fixture.score.penaltytime.PenaltyTime;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Score {
	@Id
	private int id;
	@OneToOne(cascade = CascadeType.PERSIST)
	private HalfTime halfTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private FullTime fullTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private ExtraTime extraTime;
	@OneToOne(cascade = CascadeType.PERSIST)
	private PenaltyTime penaltyTime;
	@OneToOne
	@MapsId
	private Fixture fixture;
	@Override
	public String toString() {
		return "Score [id=" + id + ", halfTime=" + halfTime + ", fullTime=" + fullTime + ", extraTime=" + extraTime
				+ ", penaltyTime=" + penaltyTime + "]";
	}
	
	public Score setScore(Score score) {
		this.extraTime = score.getExtraTime();
		this.fullTime = score.getFullTime();
		this.penaltyTime = score.getPenaltyTime();
		this.halfTime = score.getHalfTime();
		return this;
	}
	
}
