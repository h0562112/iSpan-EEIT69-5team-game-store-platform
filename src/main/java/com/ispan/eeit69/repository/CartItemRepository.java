package com.ispan.eeit69.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByCartIdAndItemId(Integer cartId, Integer itemId);
}
