package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 一次結帳產生一筆訂單（購買記錄） */
@Entity
@Table(name = "orders")
public class PurchaseOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer userId;

	/** 原價小計 */
	private Integer subtotal;

	/** 優惠券折抵金額（沒用券就是 0） */
	private Integer discountAmount;

	/** 實付金額 */
	private Integer totalAmount;

	/** 結帳當下使用的優惠券代碼，沒用券為 null */
	private String couponCode;

	private Timestamp createTime;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> items = new ArrayList<>();

	public void addItem(OrderItem item) {
		item.setOrder(this);
		items.add(item);
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Integer getUserId() { return userId; }
	public void setUserId(Integer userId) { this.userId = userId; }
	public Integer getSubtotal() { return subtotal; }
	public void setSubtotal(Integer subtotal) { this.subtotal = subtotal; }
	public Integer getDiscountAmount() { return discountAmount; }
	public void setDiscountAmount(Integer discountAmount) { this.discountAmount = discountAmount; }
	public Integer getTotalAmount() { return totalAmount; }
	public void setTotalAmount(Integer totalAmount) { this.totalAmount = totalAmount; }
	public String getCouponCode() { return couponCode; }
	public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
	public Timestamp getCreateTime() { return createTime; }
	public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }
	public List<OrderItem> getItems() { return items; }
	public void setItems(List<OrderItem> items) { this.items = items; }
}
