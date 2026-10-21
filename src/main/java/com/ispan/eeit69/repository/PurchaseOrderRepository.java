package com.ispan.eeit69.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	List<PurchaseOrder> findByUserIdOrderByCreateTimeDescIdDesc(Integer userId);

	List<PurchaseOrder> findAllByOrderByCreateTimeDescIdDesc();
}
