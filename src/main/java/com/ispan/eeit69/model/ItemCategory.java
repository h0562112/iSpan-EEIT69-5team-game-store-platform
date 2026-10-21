package com.ispan.eeit69.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "itemCategory")
public class ItemCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categoryName;
    @OneToMany(mappedBy = "itemCategoryId")
    private List<Item> items;

	public ItemCategory() {
		super();

		
	}



	public ItemCategory(Integer id, String categoryName, List<Item> items) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.items = items;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
	    return "ItemCategory [id=" + id + ", categoryName=" + categoryName + "]";



}
}