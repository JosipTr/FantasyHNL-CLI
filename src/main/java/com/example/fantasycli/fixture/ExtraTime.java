package com.example.fantasycli.fixture;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "extra_times")
public class ExtraTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Integer home;
	private Integer away;
}
