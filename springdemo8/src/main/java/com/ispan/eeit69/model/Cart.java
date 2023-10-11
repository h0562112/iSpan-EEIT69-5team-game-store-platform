package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.eeit69.utils.SystemService;
@Entity
@Table(name="cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer cartId;

    private Integer quantity;
    private Integer  price;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;  // 用戶外鍵

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;  // 商品外鍵
    @JsonIgnore
    private Clob    picture;
    @Transient
    private String image;
    
    @Autowired

    public Cart() {
		super();
	}

	public Cart(Integer cartId) {
		super();
		this.cartId = cartId;
	}

	public Cart(Integer cartId,Integer quantity ,Integer  price
			, Clob picture,User user,Item item
	) {
		super();
		this.cartId = cartId;

		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
		this.user =user;
		this.item = item;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}



	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Clob getPicture() {
		return picture;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDataUri() throws Exception {
		return SystemService.clobToString(picture);
	}

	public void setPicture(Clob picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId +  ", quantity=" + quantity
				+ ", price=" + price + ", picture=" + picture + "]";
	}


	
	

	


    
    
}
