package com.example.fantasycli.fixture.event.time;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EventTime {
	@Id
	private int id;
	private Integer elapsed;
	private Integer extra;
}
