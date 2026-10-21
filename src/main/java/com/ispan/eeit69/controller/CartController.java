package com.ispan.eeit69.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit69.model.Cart;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.CartItemRepository;

@Controller
public class CartController {

	@Autowired
	private CartItemRepository cartItemRepository;

	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return "redirect:/login";
		}
		List<Cart> carts = cartItemRepository.findByUserId(currentUser.getId());
		int total = 0;
		for (Cart cart : carts) {
			if (cart.getPrice() != null) {
				total += cart.getPrice();
			}
		}
		model.addAttribute("carts", carts);
		model.addAttribute("total", total);
		return "cart";
	}
}
