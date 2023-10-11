package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "review")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String reviewText;
	private Integer rating;

//	private String  dataUri;
	private Timestamp creationTime;

	@ManyToOne
	@JoinColumn(name = "itemsId", referencedColumnName = "id")
	@JsonBackReference
	private Item itemsId;
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonIgnoreProperties({"reviews", "password", "email", "carts", "roles"})
	private User userId;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Integer id, String reviewText, Integer rating, Timestamp creationTime, Item items, User userId) {
		super();
		this.id = id;

		this.reviewText = reviewText;
		this.rating = rating;

		this.creationTime = creationTime;

		this.itemsId = items;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Item getItemsId() {
		return itemsId;
	}

	public void setItemsId(Item itemsId) {
		this.itemsId = itemsId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewText=" + reviewText + ", userId=" + userId + ", rating=" + rating
				+ ", creationTime=" + creationTime + ", itemsId=" + itemsId + "]";
	}

}
