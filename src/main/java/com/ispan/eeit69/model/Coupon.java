package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 優惠券種類（由管理員建立） */
@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String AMOUNT = "AMOUNT"; // 折抵固定金額
	public static final String PERCENT = "PERCENT"; // 折抵百分比（例如 10 = 打九折）

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 50)
	private String code;

	private String description;

	@Column(length = 10)
	private String discountType;

	private Integer discountValue;

	/** 消費滿多少才能使用，0 代表不限 */
	private Integer minSpend = 0;

	/** 到期時間，null 代表永久有效 */
	private Timestamp expireTime;

	private Boolean active = true;

	private Timestamp createTime;

	public boolean isExpired() {
		return expireTime != null && expireTime.getTime() < System.currentTimeMillis();
	}

	/** 畫面上顯示用的折扣說明 */
	public String getDiscountText() {
		if (PERCENT.equals(discountType)) {
			return "折抵 " + discountValue + "%";
		}
		return "折抵 $" + discountValue;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getDiscountType() { return discountType; }
	public void setDiscountType(String discountType) { this.discountType = discountType; }
	public Integer getDiscountValue() { return discountValue; }
	public void setDiscountValue(Integer discountValue) { this.discountValue = discountValue; }
	public Integer getMinSpend() { return minSpend; }
	public void setMinSpend(Integer minSpend) { this.minSpend = minSpend; }
	public Timestamp getExpireTime() { return expireTime; }
	public void setExpireTime(Timestamp expireTime) { this.expireTime = expireTime; }
	public Boolean getActive() { return active; }
	public void setActive(Boolean active) { this.active = active; }
	public Timestamp getCreateTime() { return createTime; }
	public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }
}
