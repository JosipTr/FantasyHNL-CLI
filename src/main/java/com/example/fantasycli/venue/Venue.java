package com.example.fantasycli.venue;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "venues")
public class Venue {
	@Id
	private int id;
	@Column(unique = true)
	private String name;
	private String city;
	private Integer capacity;
	@Column(unique = true)
	private String address;
	private String image;
}
