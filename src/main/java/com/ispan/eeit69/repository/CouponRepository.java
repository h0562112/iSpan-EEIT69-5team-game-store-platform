package com.ispan.eeit69.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	Coupon findByCode(String code);

	List<Coupon> findAllByOrderByIdDesc();
}
