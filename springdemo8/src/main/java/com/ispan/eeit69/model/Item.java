package com.ispan.eeit69.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.eeit69.utils.SystemService;

@Entity
@Table(name = "items")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mp4;
	private Integer itemStock;
	private String itemName;
	private Integer itemPrice;
	@JsonIgnore
	private Clob itempicture;
	@JsonIgnore
	private Clob itempicture1;
	@JsonIgnore
	private Clob itempicture2;

	private String itemDescription;
	private Timestamp createTime;
	private Timestamp lastTime;
	private String discount;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ItemCategory itemCategoryId;
    @OneToMany(mappedBy = "itemsId")
    private List<Review> reviews;
    @OneToMany(mappedBy = "item")
    private List<Cart> cartItems;  // 購物車項目列表
    
    private String itemId;
    
    @Transient  
    private String image;
    @Transient
    private String filename;
    
    
	public Item() {
		super();

	}

	
	public Item(Integer id, Integer itemStock, String itemName, Integer itemPrice, Clob itempicture,
			Clob itempicture1,Clob itempicture2,String itemDescription, Timestamp createTime, 
			Timestamp lastTime, String discount,ItemCategory itemCategoryId,List<Review> reviews,String
			mp4,List<Cart> cartItems) {
		super();
		this.id = id;
		this.itemStock = itemStock;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itempicture = itempicture;
		this.itempicture1 = itempicture1;
		this.itempicture2 = itempicture2;
		this.itemDescription = itemDescription;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.discount = discount;
		this.itemCategoryId = itemCategoryId;
		this.reviews = reviews;
		this.mp4 = mp4;
		this.cartItems = cartItems;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Cart> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}


	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public Integer getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}


	public Clob getItempicture() {
		return itempicture;
	}

	public void setItempicture(Clob itempicture) {
		this.itempicture = itempicture;
	}

	public Clob getItempicture1() {
		return itempicture1;
	}


	public void setItempicture1(Clob itempicture1) {
		this.itempicture1 = itempicture1;
	}


	public Clob getItempicture2() {
		return itempicture2;
	}


	public void setItempicture2(Clob itempicture2) {
		this.itempicture2 = itempicture2;
	}


	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public ItemCategory getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(ItemCategory itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDataUri() throws Exception {
		return SystemService.clobToString(itempicture);
	}
	public String getDataUri1() throws Exception {
		return SystemService.clobToString(itempicture1);
	}
	public String getDataUri2() throws Exception {
		return SystemService.clobToString(itempicture2);
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public String getMp4() {
		return mp4;
	}


	public void setMp4(String mp4) {
		this.mp4 = mp4;
	}


	@Override
	public String toString() {
		return "Items [id=" + id + ", mp4=" + mp4 + ", itemStock=" + itemStock + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + ", itempicture=" + itempicture + ", itempicture1=" + itempicture1
				+ ", itempicture2=" + itempicture2 + ", itemDescription=" + itemDescription + ", createTime="
				+ createTime + ", lastTime=" + lastTime + ", discount=" + discount + ", itemCategoryId="
				+ itemCategoryId + "]";
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	




	

}
