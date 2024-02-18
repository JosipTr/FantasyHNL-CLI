package com.example.fantasycli.player.birth;

import com.example.fantasycli.country.Country;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "births")
public class Birth {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String date;
	private String place;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country birth_country;
	
	public void setBirth(Birth birth) {
		this.date = birth.getDate();
		this.place = birth.getPlace();
		this.birth_country = birth.getBirth_country();
	}
}
