package com.example.fantasycli.fixture.statistic;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.statistic.card.Card;
import com.example.fantasycli.fixture.statistic.dribble.Dribble;
import com.example.fantasycli.fixture.statistic.duel.Duel;
import com.example.fantasycli.fixture.statistic.foul.Foul;
import com.example.fantasycli.fixture.statistic.game.Game;
import com.example.fantasycli.fixture.statistic.goal.Goal;
import com.example.fantasycli.fixture.statistic.pass.Pass;
import com.example.fantasycli.fixture.statistic.penalty.Penalty;
import com.example.fantasycli.fixture.statistic.shot.Shot;
import com.example.fantasycli.fixture.statistic.tackle.Tackle;
import com.example.fantasycli.player.Player;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@EqualsAndHashCode
@Setter
@Table(name = "statistics")
public class Statistic {
	@EmbeddedId
	private StatisticId id;
	private Integer offsides;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Game game;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Shot shot;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Pass pass;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Tackle tackle;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Duel duel;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Dribble dribble;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Foul foul;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Card card;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Penalty penalty;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "statistic")
	private Goal goal;
	@ManyToOne(cascade = CascadeType.MERGE)
	@MapsId("playerId")
	private Player player;
	@ManyToOne(cascade = CascadeType.MERGE)
	@MapsId("fixtureId")
	private Fixture fixture;
	
	public Statistic() {
		super();
		this.id = new StatisticId();
	}
	
	@Override
	public String toString() {
		return "Statistic [id=" + id + ", offsides=" + offsides + ", game=" + game + ", shot=" + shot + ", pass=" + pass
				+ ", tackle=" + tackle + ", duel=" + duel + ", dribble=" + dribble + ", foul=" + foul + ", card=" + card
				+ ", penalty=" + penalty + ", goal=" + goal + "]";
	}
}
