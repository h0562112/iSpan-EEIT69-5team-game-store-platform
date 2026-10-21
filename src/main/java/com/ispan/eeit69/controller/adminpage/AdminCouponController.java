package com.ispan.eeit69.controller.adminpage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.CouponRepository;
import com.ispan.eeit69.repository.UserRepository;
import com.ispan.eeit69.service.CouponService;

/** 後台 - 優惠券：建立、啟用/停用、派發 */
@Controller
@RequestMapping("/adminpages/coupon")
public class AdminCouponController {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CouponService couponService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("coupons", couponRepository.findAllByOrderByIdDesc());
		return "adminpages/coupons";
	}

	@PostMapping("/create")
	public String create(@RequestParam String code, @RequestParam(defaultValue = "") String description,
			@RequestParam String discountType, @RequestParam Integer discountValue,
			@RequestParam(defaultValue = "0") Integer minSpend, @RequestParam(required = false) String expireDate,
			RedirectAttributes ra) {
		code = code.trim();
		if (code.isEmpty() || code.length() > 50) {
			return fail(ra, "優惠碼不可空白，且最多 50 個字");
		}
		if (couponRepository.findByCode(code) != null) {
			return fail(ra, "優惠碼 " + code + " 已經存在");
		}
		boolean percent = Coupon.PERCENT.equals(discountType);
		if (!percent && !Coupon.AMOUNT.equals(discountType)) {
			return fail(ra, "折扣類型錯誤");
		}
		if (discountValue == null || discountValue < 1 || (percent && discountValue > 100)) {
			return fail(ra, percent ? "折抵百分比要在 1 到 100 之間" : "折抵金額必須大於 0");
		}
		if (minSpend == null || minSpend < 0) {
			return fail(ra, "消費門檻不可小於 0");
		}

		Coupon coupon = new Coupon();
		coupon.setCode(code);
		coupon.setDescription(description.trim());
		coupon.setDiscountType(discountType);
		coupon.setDiscountValue(discountValue);
		coupon.setMinSpend(minSpend);
		coupon.setActive(true);
		coupon.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if (expireDate != null && !expireDate.trim().isEmpty()) {
			try {
				// 到期日當天 23:59:59 前都還能用
				coupon.setExpireTime(Timestamp.valueOf(LocalDate.parse(expireDate.trim()).atTime(23, 59, 59)));
			} catch (DateTimeParseException e) {
				return fail(ra, "到期日格式錯誤");
			}
		}
		couponRepository.save(coupon);
		ra.addFlashAttribute("message", "已建立優惠券 " + code);
		return "redirect:/adminpages/coupon";
	}

	@PostMapping("/{id}/toggle")
	public String toggle(@PathVariable Integer id, RedirectAttributes ra) {
		Coupon coupon = couponRepository.findById(id).orElse(null);
		if (coupon == null) {
			return fail(ra, "找不到這張優惠券");
		}
		coupon.setActive(!Boolean.TRUE.equals(coupon.getActive()));
		couponRepository.save(coupon);
		ra.addFlashAttribute("message", coupon.getCode() + (coupon.getActive() ? " 已啟用" : " 已停用"));
		return "redirect:/adminpages/coupon";
	}

	/** target 填 all 就派發給全部會員，否則視為會員帳號 */
	@PostMapping("/{id}/issue")
	public String issue(@PathVariable Integer id, @RequestParam String target, RedirectAttributes ra) {
		Coupon coupon = couponRepository.findById(id).orElse(null);
		if (coupon == null) {
			return fail(ra, "找不到這張優惠券");
		}
		target = target.trim();
		if ("all".equalsIgnoreCase(target)) {
			int issued = 0;
			for (User user : userRepository.findAll()) {
				if (couponService.issue(user.getId(), coupon)) {
					issued++;
				}
			}
			ra.addFlashAttribute("message", "已派發 " + coupon.getCode() + " 給 " + issued + " 位會員（已持有者略過）");
		} else {
			User user = userRepository.findByUsername(target);
			if (user == null) {
				return fail(ra, "找不到會員帳號：" + target);
			}
			boolean issued = couponService.issue(user.getId(), coupon);
			ra.addFlashAttribute("message",
					issued ? "已派發 " + coupon.getCode() + " 給 " + target : target + " 已經持有這張優惠券");
		}
		return "redirect:/adminpages/coupon";
	}

	private String fail(RedirectAttributes ra, String message) {
		ra.addFlashAttribute("error", message);
		return "redirect:/adminpages/coupon";
	}
}
