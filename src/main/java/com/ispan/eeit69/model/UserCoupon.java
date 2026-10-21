package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/** 會員持有的優惠券。同一種券每人只能持有一張 */
@Entity
@Table(name = "user_coupon", uniqueConstraints = @UniqueConstraint(columnNames = { "userId", "couponId" }))
public class UserCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer userId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "couponId")
	private Coupon coupon;

	private Boolean used = false;
	private Timestamp usedTime;
	private Timestamp receivedTime;

	public UserCoupon() {
	}

	public UserCoupon(Integer userId, Coupon coupon) {
		this.userId = userId;
		this.coupon = coupon;
		this.used = false;
		this.receivedTime = new Timestamp(System.currentTimeMillis());
	}

	/** 目前可不可以使用（沒用過、券本身啟用中、沒過期） */
	public boolean isUsable() {
		return !Boolean.TRUE.equals(used) && coupon != null && Boolean.TRUE.equals(coupon.getActive())
				&& !coupon.isExpired();
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Integer getUserId() { return userId; }
	public void setUserId(Integer userId) { this.userId = userId; }
	public Coupon getCoupon() { return coupon; }
	public void setCoupon(Coupon coupon) { this.coupon = coupon; }
	public Boolean getUsed() { return used; }
	public void setUsed(Boolean used) { this.used = used; }
	public Timestamp getUsedTime() { return usedTime; }
	public void setUsedTime(Timestamp usedTime) { this.usedTime = usedTime; }
	public Timestamp getReceivedTime() { return receivedTime; }
	public void setReceivedTime(Timestamp receivedTime) { this.receivedTime = receivedTime; }
}
