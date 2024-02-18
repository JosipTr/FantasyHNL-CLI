package com.example.fantasycli.fixture.event.time;

import com.example.fantasycli.fixture.Fixture;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class EventTime {
	@Id
	private int id;
	private Integer elapsed;
	private Integer extra;
	@ManyToOne
	private Fixture fixture;
}
