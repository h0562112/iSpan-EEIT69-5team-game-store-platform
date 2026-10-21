package com.ispan.eeit69.controller.adminpage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.PurchaseOrderRepository;
import com.ispan.eeit69.repository.UserRepository;

/** 後台 - 訂單查詢 */
@Controller
@RequestMapping("/adminpages/orders")
public class AdminOrderController {

	@Autowired
	private PurchaseOrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String list(Model model) {
		// 訂單只存 userId，這裡一次查好帳號名稱給畫面顯示
		Map<Integer, String> usernames = new HashMap<>();
		for (User user : userRepository.findAll()) {
			usernames.put(user.getId(), user.getUsername());
		}
		model.addAttribute("orders", orderRepository.findAllByOrderByCreateTimeDescIdDesc());
		model.addAttribute("usernames", usernames);
		return "adminpages/orders";
	}
}
