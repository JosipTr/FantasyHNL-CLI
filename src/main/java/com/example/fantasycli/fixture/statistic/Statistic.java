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
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("playerId")
	private Player player;
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("fixtureId")
	private Fixture fixture;
	
	public Statistic() {
		super();
		this.id = new StatisticId();
	}
	
	public void deleteStatistic() {
//		this.id = null;
		this.card.setStatistic(null);
		this.dribble.setStatistic(null);
		this.duel.setStatistic(null);
//		this.fixture.setStatistics(null);
		this.foul.setStatistic(null);
		this.game.setStatistic(null);
		this.goal.setStatistic(null);
//		this.offsides = null;
		this.pass.setStatistic(null);
		this.penalty.setStatistic(null);
//		this.player.setStatistics(null);
		this.shot.setStatistic(null);
		this.tackle.setStatistic(null);
	}
	
	public void removeStatistic() {
		this.getCard().setStatistic(null);
		this.getDribble().setStatistic(null);
		this.getCard().setStatistic(null);
		this.getDuel().setStatistic(null);
		this.getFoul().setStatistic(null);
		this.getGame().setStatistic(null);
		this.getGoal().setStatistic(null);
		this.getPass().setStatistic(null);
		this.getPenalty().setStatistic(null);
		this.player = null;
		this.fixture = null;
		this.getShot().setStatistic(null);
		this.getTackle().setStatistic(null);
	}
	
	@Override
	public String toString() {
		return "Statistic [id=" + id + ", offsides=" + offsides + ", game=" + game + ", shot=" + shot + ", pass=" + pass
				+ ", tackle=" + tackle + ", duel=" + duel + ", dribble=" + dribble + ", foul=" + foul + ", card=" + card
				+ ", penalty=" + penalty + ", goal=" + goal + "]";
	}
}
