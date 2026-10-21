package com.ispan.eeit69.controller.membercontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.UserCouponRepository;
import com.ispan.eeit69.service.CouponService;
import com.ispan.eeit69.service.CouponService.CouponException;

/** 會員中心 - 我的優惠券 */
@Controller
public class MyCouponController {

	@Autowired
	private UserCouponRepository userCouponRepository;

	@Autowired
	private CouponService couponService;

	@GetMapping("/coupon")
	public String myCoupons(Model model, HttpSession session) {
		User me = (User) session.getAttribute("myUser");
		if (me == null) {
			return "redirect:/login";
		}
		model.addAttribute("userCoupons", userCouponRepository.findByUserIdOrderByReceivedTimeDescIdDesc(me.getId()));
		return "member-profile/coupon";
	}

	/** 輸入優惠碼領券 */
	@PostMapping("/api/coupons/claim")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> claim(@RequestParam String code, HttpSession session) {
		User me = (User) session.getAttribute("myUser");
		Map<String, Object> body = new HashMap<>();
		if (me == null) {
			body.put("success", false);
			body.put("message", "請先登入");
			return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
		}
		try {
			couponService.claimByCode(me.getId(), code);
			body.put("success", true);
			body.put("message", "領取成功！");
		} catch (CouponException e) {
			body.put("success", false);
			body.put("message", e.getMessage());
		}
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/** 購物車用：目前可使用的優惠券 */
	@GetMapping("/api/coupons/usable")
	@ResponseBody
	public Object usable(HttpSession session) {
		User me = (User) session.getAttribute("myUser");
		if (me == null) {
			return new java.util.ArrayList<>();
		}
		java.util.List<Map<String, Object>> list = new java.util.ArrayList<>();
		for (com.ispan.eeit69.model.UserCoupon uc : userCouponRepository
				.findByUserIdOrderByReceivedTimeDescIdDesc(me.getId())) {
			if (uc.isUsable()) {
				Map<String, Object> row = new HashMap<>();
				row.put("id", uc.getId());
				row.put("label", uc.getCoupon().getCode() + "（" + uc.getCoupon().getDiscountText() + "）");
				list.add(row);
			}
		}
		return list;
	}
}
