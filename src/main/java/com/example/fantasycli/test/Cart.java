package com.example.fantasycli.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Item> items = new HashSet<>();

	public void addItem(Item item) {
		item.setCart(this);
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
			item.setCart(null);
			itemIterator.remove();
		}
//		this.items.clear();
//		this.setItems(tmp);
		this.getItems().addAll(tmp);
	}
	
//	public void removeAllItems() {
//		for (Iterator<Item> featureIterator = items.iterator(); featureIterator.hasNext();) {
//			Item feature = featureIterator.next();
//			feature.setCart(null);
//			featureIterator.remove();
//		}
//		this.items.clear();
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//
//		if (!(o instanceof Cart))
//			return false;
//
//		Cart other = (Cart) o;
//
//		return id == (other.getId());
//	}
//
//	@Override
//	public int hashCode() {
//		return getClass().hashCode();
//	}
}
