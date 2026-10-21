package com.ispan.eeit69.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Cart;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.player_Games_library;
import com.ispan.eeit69.repository.CartItemRepository;
import com.ispan.eeit69.repository.ItemsRepository;
import com.ispan.eeit69.repository.PlayerGamesLibraryRepository;

@RestController
@RequestMapping("/api") // 基本的API路徑
public class CartApiController {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private PlayerGamesLibraryRepository libraryRepository;

	private ResponseEntity<Map<String, Object>> result(HttpStatus status, boolean success, String message) {
		Map<String, Object> response = new HashMap<>();
		response.put("success", success);
		response.put("message", message);
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("/addToCart")
	public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload,
			HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return result(HttpStatus.UNAUTHORIZED, false, "請先登入");
		}

		String itemId = payload.get("itemId");
		if (itemId == null || itemId.isEmpty()) {
			return result(HttpStatus.BAD_REQUEST, false, "商品ID不能為空");
		}

		int id;
		try {
			id = Integer.parseInt(itemId);
		} catch (NumberFormatException e) {
			return result(HttpStatus.BAD_REQUEST, false, "商品ID格式錯誤");
		}

		Optional<Item> optionalItem = itemsRepository.findById(id);
		if (!optionalItem.isPresent()) {
			return result(HttpStatus.NOT_FOUND, false, "商品不存在");
		}
		Item item = optionalItem.get();

		if (libraryRepository.existsByPlayerIDAndGameID(currentUser.getId(), item.getId())) {
			return result(HttpStatus.CONFLICT, false, "您已擁有這款遊戲");
		}

		// 已在購物車內就不重複新增
		if (cartItemRepository.findByUserIdAndItemId(currentUser.getId(), item.getId()).isPresent()) {
			return result(HttpStatus.OK, true, "商品已在購物車內");
		}

		Cart newCart = new Cart();
		newCart.setItem(item);
		newCart.setUser(currentUser);
		newCart.setPrice(item.getItemPrice());
		newCart.setQuantity(1);
		newCart.setPicture(item.getItempicture());
		cartItemRepository.save(newCart);

		return result(HttpStatus.OK, true, "成功加入購物車");
	}

	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<Map<String, Object>> removeFromCart(@PathVariable Integer cartId, HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return result(HttpStatus.UNAUTHORIZED, false, "請先登入");
		}
		Optional<Cart> cart = cartItemRepository.findById(cartId);
		if (!cart.isPresent() || cart.get().getUser() == null || cart.get().getUser().getId() != currentUser.getId()) {
			return result(HttpStatus.NOT_FOUND, false, "購物車項目不存在");
		}
		cartItemRepository.delete(cart.get());
		return result(HttpStatus.OK, true, "已移除");
	}

	/** 結帳：把購物車內的遊戲加入遊戲庫並清空購物車 */
	@PostMapping("/cart/checkout")
	@Transactional
	public ResponseEntity<Map<String, Object>> checkout(HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return result(HttpStatus.UNAUTHORIZED, false, "請先登入");
		}
		List<Cart> carts = cartItemRepository.findByUserId(currentUser.getId());
		if (carts.isEmpty()) {
			return result(HttpStatus.BAD_REQUEST, false, "購物車是空的");
		}
		for (Cart cart : carts) {
			Integer gameId = cart.getItem().getId();
			if (!libraryRepository.existsByPlayerIDAndGameID(currentUser.getId(), gameId)) {
				libraryRepository.save(new player_Games_library(null, currentUser.getId(), gameId));
			}
		}
		cartItemRepository.deleteAll(carts);
		return result(HttpStatus.OK, true, "結帳完成，遊戲已加入您的遊戲庫");
	}
}
