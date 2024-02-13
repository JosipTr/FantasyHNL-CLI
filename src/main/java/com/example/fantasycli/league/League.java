package com.example.fantasycli.league;

import com.example.fantasycli.country.Country;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "leagues")
public class League {
	@Id
	private int id;
	private String name;
	private String type;
	private String logo;
	@ManyToOne
	private Country country;
}
