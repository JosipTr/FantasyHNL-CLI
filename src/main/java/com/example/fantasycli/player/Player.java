package com.example.fantasycli.player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.example.fantasycli.fixture.statistic.Statistic;
import com.example.fantasycli.player.birth.Birth;
import com.example.fantasycli.team.Team;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "players")
public class Player {
	@Id
	private int id;
	private String name;
	private Integer age;
	private Integer number;
	private String position;
	private String firstname;
	private String lastname;
	private String nationality;
	private String height;
	private String weight;
	private Boolean injured;
	private String photo;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Birth birth;
	@ManyToOne
	private Team team;
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Statistic> statistics = new HashSet<>();

	public void addStatistic(Statistic statistic) {
		statistic.setPlayer(this);
//		this.statistics.add(statistic);
		this.getStatistics().add(statistic);
	}
	
//	public void removeStatistic(Statistic statistic) {
//		this.statistics.remove(statistic);
//		statistic.setPlayer(null);
//		statistic.deleteStatistic();
//	}
//	
//	public void updateStatistics(Collection<Statistic> newStatistics) {
//	    Set<Statistic> currentStatistics = new HashSet<>(this.statistics);
//
//	    currentStatistics.removeAll(newStatistics);
//	    currentStatistics.forEach(this::removeStatistic);
//	    
//	    newStatistics.removeAll(this.statistics);
//	    newStatistics.forEach(this::addStatistic);
//	    
//	    this.statistics.clear();
//	    this.statistics.addAll(newStatistics);
//	}
//	
//	public void setOppositeNull(Collection<Statistic> newStatistics) {
//		newStatistics.forEach(t -> t = null);
//	}
	
	
	public void removeAllStatistics() {
		Set<Statistic> tmp = new HashSet<>(this.statistics);
		for (Iterator<Statistic> statisticIterator = tmp.iterator(); statisticIterator.hasNext();) {
			Statistic statistic = statisticIterator.next();
//			statistic.removeStatistic();
			statistic.setPlayer(null);
			statisticIterator.remove();
		}
//		this.items.clear();
//		this.getStatistics().clear();
//		this.setItems(tmp);
		this.getStatistics().addAll(tmp);
	}
	
	public void updateStatistics(Set<Statistic> statisticSet) {
		Set<Statistic> tmp = new HashSet<>(this.statistics);
		for (Iterator<Statistic> statisticIterator = tmp.iterator(); statisticIterator.hasNext();) {
			Statistic statistic = statisticIterator.next();
			statistic.setPlayer(null);
			statisticIterator.remove();
		}
		this.getStatistics().clear();
//		this.setItems(tmp);
		this.getStatistics().addAll(tmp);
		this.getStatistics().addAll(statisticSet);
	}
	
	public void setPlayer(Player player) {
		this.name = player.getName();
		this.age = player.getAge();
		this.number = player.getNumber();
		this.position = player.getPosition();
		this.firstname = player.getFirstname();
		this.lastname = player.getLastname();
		this.nationality = player.getNationality();
		this.height = player.getHeight();
		this.weight = player.getWeight();
		this.injured = player.getInjured();
		this.photo = player.getPhoto();
		this.birth = player.getBirth();
		this.team = player.getTeam();
		this.statistics = player.getStatistics();
	}
}
