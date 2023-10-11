package com.ispan.eeit69.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Page<Review> findByItemsIdAndRating(Item itemId, Integer rating, Pageable pageable);
    Page<Review> findByItemsId(Item itemId, Pageable pageable);
}
