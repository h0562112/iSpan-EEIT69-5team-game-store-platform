package com.ispan.eeit69.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Coupon;
import com.ispan.eeit69.model.UserCoupon;
import com.ispan.eeit69.repository.CouponRepository;
import com.ispan.eeit69.repository.UserCouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private UserCouponRepository userCouponRepository;

	/** 優惠券不能用時丟出，message 可以直接顯示給使用者 */
	public static class CouponException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public CouponException(String message) {
			super(message);
		}
	}

	/** 驗證這張券現在能不能用在 subtotal 這個金額上，回傳折抵金額 */
	public int calculateDiscount(UserCoupon userCoupon, int subtotal) {
		if (userCoupon == null || userCoupon.getCoupon() == null) {
			throw new CouponException("找不到這張優惠券");
		}
		if (Boolean.TRUE.equals(userCoupon.getUsed())) {
			throw new CouponException("這張優惠券已經使用過了");
		}
		Coupon coupon = userCoupon.getCoupon();
		if (!Boolean.TRUE.equals(coupon.getActive())) {
			throw new CouponException("這張優惠券已停用");
		}
		if (coupon.isExpired()) {
			throw new CouponException("這張優惠券已過期");
		}
		int minSpend = coupon.getMinSpend() == null ? 0 : coupon.getMinSpend();
		if (subtotal < minSpend) {
			throw new CouponException("消費滿 $" + minSpend + " 才能使用這張優惠券");
		}
		int value = coupon.getDiscountValue() == null ? 0 : coupon.getDiscountValue();
		int discount = Coupon.PERCENT.equals(coupon.getDiscountType()) ? subtotal * value / 100 : value;
		return Math.max(0, Math.min(discount, subtotal));
	}

	/** 發一張券給會員。已經有同一種券就回傳 false */
	public boolean issue(Integer userId, Coupon coupon) {
		if (userCouponRepository.existsByUserIdAndCouponId(userId, coupon.getId())) {
			return false;
		}
		userCouponRepository.save(new UserCoupon(userId, coupon));
		return true;
	}

	/** 會員輸入優惠碼領券 */
	public UserCoupon claimByCode(Integer userId, String code) {
		Coupon coupon = code == null ? null : couponRepository.findByCode(code.trim());
		if (coupon == null || !Boolean.TRUE.equals(coupon.getActive())) {
			throw new CouponException("優惠碼不存在或已停用");
		}
		if (coupon.isExpired()) {
			throw new CouponException("這個優惠碼已過期");
		}
		if (userCouponRepository.existsByUserIdAndCouponId(userId, coupon.getId())) {
			throw new CouponException("你已經領過這張優惠券了");
		}
		return userCouponRepository.save(new UserCoupon(userId, coupon));
	}

	public void markUsed(UserCoupon userCoupon) {
		userCoupon.setUsed(true);
		userCoupon.setUsedTime(new Timestamp(System.currentTimeMillis()));
		userCouponRepository.save(userCoupon);
	}
}
