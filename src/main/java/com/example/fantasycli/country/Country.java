package com.example.fantasycli.country;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "countries")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(unique = true)
	private String name;
	private String code;
	private String flag;
}
