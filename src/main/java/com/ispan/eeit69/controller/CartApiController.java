package com.ispan.eeit69.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Cart;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.OrderItem;
import com.ispan.eeit69.model.PurchaseOrder;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.UserCoupon;
import com.ispan.eeit69.model.player_Games_library;
import com.ispan.eeit69.repository.CartItemRepository;
import com.ispan.eeit69.repository.ItemsRepository;
import com.ispan.eeit69.repository.PlayerGamesLibraryRepository;
import com.ispan.eeit69.repository.PurchaseOrderRepository;
import com.ispan.eeit69.repository.UserCouponRepository;
import com.ispan.eeit69.service.CouponService;
import com.ispan.eeit69.service.CouponService.CouponException;

@RestController
@RequestMapping("/api") // 基本的API路徑
public class CartApiController {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private PlayerGamesLibraryRepository libraryRepository;

	@Autowired
	private PurchaseOrderRepository orderRepository;

	@Autowired
	private UserCouponRepository userCouponRepository;

	@Autowired
	private CouponService couponService;

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

	private int subtotal(List<Cart> carts) {
		int subtotal = 0;
		for (Cart cart : carts) {
			if (cart.getPrice() != null) {
				subtotal += cart.getPrice();
			}
		}
		return subtotal;
	}

	/** 試算使用某張優惠券後的金額（不會真的扣券） */
	@GetMapping("/cart/coupon-preview")
	public ResponseEntity<Map<String, Object>> couponPreview(@RequestParam Integer userCouponId,
			HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return result(HttpStatus.UNAUTHORIZED, false, "請先登入");
		}
		int subtotal = subtotal(cartItemRepository.findByUserId(currentUser.getId()));
		try {
			UserCoupon userCoupon = userCouponRepository.findByIdAndUserId(userCouponId, currentUser.getId())
					.orElse(null);
			int discount = couponService.calculateDiscount(userCoupon, subtotal);
			Map<String, Object> body = new HashMap<>();
			body.put("success", true);
			body.put("discount", discount);
			body.put("total", subtotal - discount);
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch (CouponException e) {
			return result(HttpStatus.OK, false, e.getMessage());
		}
	}

	/** 結帳：建立訂單、把購物車內的遊戲加入遊戲庫並清空購物車。可選擇性使用一張優惠券 */
	@PostMapping("/cart/checkout")
	@Transactional
	public ResponseEntity<Map<String, Object>> checkout(@RequestParam(required = false) Integer userCouponId,
			HttpSession session) {
		User currentUser = (User) session.getAttribute("myUser");
		if (currentUser == null) {
			return result(HttpStatus.UNAUTHORIZED, false, "請先登入");
		}
		List<Cart> carts = cartItemRepository.findByUserId(currentUser.getId());
		if (carts.isEmpty()) {
			return result(HttpStatus.BAD_REQUEST, false, "購物車是空的");
		}

		int subtotal = subtotal(carts);
		int discount = 0;
		UserCoupon userCoupon = null;
		if (userCouponId != null) {
			userCoupon = userCouponRepository.findByIdAndUserId(userCouponId, currentUser.getId()).orElse(null);
			try {
				discount = couponService.calculateDiscount(userCoupon, subtotal);
			} catch (CouponException e) {
				return result(HttpStatus.BAD_REQUEST, false, e.getMessage());
			}
		}

		PurchaseOrder order = new PurchaseOrder();
		order.setUserId(currentUser.getId());
		order.setSubtotal(subtotal);
		order.setDiscountAmount(discount);
		order.setTotalAmount(subtotal - discount);
		order.setCouponCode(userCoupon == null ? null : userCoupon.getCoupon().getCode());
		order.setCreateTime(new Timestamp(System.currentTimeMillis()));

		for (Cart cart : carts) {
			Integer gameId = cart.getItem().getId();
			order.addItem(new OrderItem(gameId, cart.getItem().getItemName(), cart.getPrice()));
			if (!libraryRepository.existsByPlayerIDAndGameID(currentUser.getId(), gameId)) {
				libraryRepository.save(new player_Games_library(null, currentUser.getId(), gameId));
			}
		}
		orderRepository.save(order);
		if (userCoupon != null) {
			couponService.markUsed(userCoupon);
		}
		cartItemRepository.deleteAll(carts);
		return result(HttpStatus.OK, true, "結帳完成，遊戲已加入您的遊戲庫");
	}
}
