package com.ispan.eeit69.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Item;

public interface ItemsRepository extends JpaRepository<Item, Integer> {
	List<Item> findByItemCategoryId_CategoryName(String categoryName);
	List<Item> findByDiscountIsNotNullOrderByDiscountDesc(); // 折扣不為空並按照折扣從高到低排序
    List<Item> findByItemStockLessThanOrderByItemStockAsc(Integer itemStock); // 使用正確的屬性名稱
    Optional<Item> findByitemName(String name);
}
