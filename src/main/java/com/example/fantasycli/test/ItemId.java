package com.example.fantasycli.test;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Embeddable
@ToString
@EqualsAndHashCode
public class ItemId {
	private int cartId;
	private int someId;
}
