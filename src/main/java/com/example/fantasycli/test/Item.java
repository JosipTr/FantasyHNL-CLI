package com.example.fantasycli.test;

import com.example.fantasycli.fixture.statistic.StatisticId;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
//@EqualsAndHashCode
public class Item {
	@EmbeddedId
	private ItemId id;
	private String name;
	@ManyToOne
	@MapsId("cartId")
	private Cart cart;
	@ManyToOne
	@MapsId("someId")
	private Some some;
}
