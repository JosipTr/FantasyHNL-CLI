package com.example.fantasycli.test;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.*;

@Service
@ShellComponent
@AllArgsConstructor
@Transactional
public class CartService {
	private final CartRepository cartRepository;
	private final SomeRepository someRepository;
	
	@ShellMethod(key = "cart")
	public void saveCart() {
		var cart = new Cart();
		var some = new Some();
		cartRepository.save(cart);
		someRepository.save(some);
	}
	
	@ShellMethod(key = "add to cart")
	public void addToCart() {
		var cart = cartRepository.getReferenceById(1);
		var some = someRepository.getReferenceById(1);
		var item1 = new Item();
		item1.setName("item1");
		cart.addItem(item1);
		some.addItem(item1);
//		cartRepository.save(cart);
	}
	
	@ShellMethod(key = "get cart")
	public void getCart() {
		var cart = cartRepository.getReferenceById(1);
		for (var item : cart.getItems()) {
			System.out.println(item.getName());
		}
	}
	
//	@ShellMethod(key = "remove")
//	public void removeCart() {
//		var cart = cartRepository.getReferenceById(1);
//		for (var item : cart.getItems()) {
//			cart.removeItem(item);
//		}
//	}
	
	@ShellMethod(key = "remove all")
	public void removeAll() {
		var cart = cartRepository.getReferenceById(1);
		cart.removeAllItems();
	}
	
}
