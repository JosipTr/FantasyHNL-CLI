package com.example.fantasycli.fixture.score;

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
	@OneToOne
	private HalfTime halfTime;
	@OneToOne
	private FullTime fullTime;
	@OneToOne
	private ExtraTime extraTime;
	@OneToOne
	private PenaltyTime penaltyTime;

	@Override
	public String toString() {
		return "Score [id=" + id + ", halfTime=" + halfTime + ", fullTime=" + fullTime + ", extraTime=" + extraTime
				+ ", penaltyTime=" + penaltyTime + "]";
	}
	
}
