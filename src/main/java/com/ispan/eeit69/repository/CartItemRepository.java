package com.ispan.eeit69.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.eeit69.model.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByCartIdAndItemId(Integer cartId, Integer itemId);

	@Query("select c from Cart c where c.user.id = :userId order by c.cartId")
	List<Cart> findByUserId(@Param("userId") int userId);

	@Query("select c from Cart c where c.user.id = :userId and c.item.id = :itemId")
	Optional<Cart> findByUserIdAndItemId(@Param("userId") int userId, @Param("itemId") Integer itemId);
}
