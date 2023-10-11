package com.ispan.eeit69.controller;

import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Cart;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.CartItemRepository;
import com.ispan.eeit69.repository.ItemsRepository;
import com.ispan.eeit69.repository.UserRepository;

@RestController
@RequestMapping("/api") // 基本的API路徑

public class CartApiController {
	@Autowired
	private CartItemRepository cartItemRepository; // 假設你有一個 CartRepository

	@Autowired
	private ItemsRepository itemsRepository; // 從您原本的代碼中看到的

	@Autowired
	private UserRepository userRepository; // 從您原本的代碼中看到的

	@PostMapping("/addToCart")
	public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, String> payload,
			HttpSession session) {
		String itemId = payload.get("itemId");

		Map<String, Object> response = new HashMap<>();

		// 驗證輸入
		if (itemId == null || itemId.isEmpty()) {
			response.put("success", false);
			response.put("message", "商品ID不能為空");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// 獲取當前用戶（假設你已經在Session中存有用戶資訊）
		User currentUser = (User) session.getAttribute("myUser");

		// 查找商品
		Optional<Item> optionalItem = itemsRepository.findById(Integer.parseInt(itemId));
		if (!optionalItem.isPresent()) {
			response.put("success", false);
			response.put("message", "商品不存在");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// 創建購物車項目

		Item item = optionalItem.get();
		Cart newCart = new Cart();
		newCart.setItem(item);

		newCart.setUser(currentUser);

		newCart.setPrice(item.getItemPrice());
		newCart.setQuantity(item.getItemStock());
		newCart.setPicture(item.getItempicture());
		// 保存購物車項目
		cartItemRepository.save(newCart);

		// 返回成功訊息
		response.put("success", true);
		response.put("message", "成功加入購物車");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}