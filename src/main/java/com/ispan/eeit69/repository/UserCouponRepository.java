package com.ispan.eeit69.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.UserCoupon;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Integer> {
	List<UserCoupon> findByUserIdOrderByReceivedTimeDescIdDesc(Integer userId);

	Optional<UserCoupon> findByIdAndUserId(Integer id, Integer userId);

	boolean existsByUserIdAndCouponId(Integer userId, Integer couponId);
}
