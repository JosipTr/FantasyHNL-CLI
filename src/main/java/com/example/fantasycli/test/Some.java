package com.example.fantasycli.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Some {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@OneToMany(mappedBy = "some", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Item> items = new HashSet<>();

	public void addItem(Item item) {
		item.setSome(this);
		this.getItems().add(item);
//		this.items.add(item);
//		item.setCart(this);
	}

//	public void removeItem(Item item) {
//		item.setCart(null);
////		this.items.remove(item);
//		this.getItems().remove(item);
//	}

//	public void removeAllItems() {
//		Set<Item> tmp = new HashSet<>(this.items);
//		
//		tmp.forEach(this::removeItem);
//		
//		this.getItems().clear();
//		this.getItems().addAll(tmp);
//	}

	public void removeAllItems() {
		Set<Item> tmp = new HashSet<>(this.items);
		for (Iterator<Item> itemIterator = tmp.iterator(); itemIterator.hasNext();) {
			Item item = itemIterator.next();
			item.setSome(null);
			itemIterator.remove();
		}
//		this.items.clear();
//		this.setItems(tmp);
		this.getItems().addAll(tmp);
	}
}
