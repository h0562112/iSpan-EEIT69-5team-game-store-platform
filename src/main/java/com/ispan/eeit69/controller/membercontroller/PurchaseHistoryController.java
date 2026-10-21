package com.ispan.eeit69.controller.membercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.PurchaseOrderRepository;

/** 會員中心 - 購買記錄 */
@Controller
public class PurchaseHistoryController {

	@Autowired
	private PurchaseOrderRepository orderRepository;

	@GetMapping("/orders")
	public String orders(Model model, HttpSession session) {
		User me = (User) session.getAttribute("myUser");
		if (me == null) {
			return "redirect:/login";
		}
		// 只查登入者自己的訂單
		model.addAttribute("orders", orderRepository.findByUserIdOrderByCreateTimeDescIdDesc(me.getId()));
		return "member-profile/purchase-history";
	}
}
