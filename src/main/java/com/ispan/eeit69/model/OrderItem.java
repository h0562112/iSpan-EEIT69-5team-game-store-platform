package com.ispan.eeit69.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 訂單明細。商品名稱與價格是結帳當下的快照，之後商品改價或下架不影響購買記錄 */
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private PurchaseOrder order;

	private Integer itemId;
	private String itemName;
	private Integer price;

	public OrderItem() {
	}

	public OrderItem(Integer itemId, String itemName, Integer price) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public PurchaseOrder getOrder() { return order; }
	public void setOrder(PurchaseOrder order) { this.order = order; }
	public Integer getItemId() { return itemId; }
	public void setItemId(Integer itemId) { this.itemId = itemId; }
	public String getItemName() { return itemName; }
	public void setItemName(String itemName) { this.itemName = itemName; }
	public Integer getPrice() { return price; }
	public void setPrice(Integer price) { this.price = price; }
}
