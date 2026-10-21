package com.ispan.eeit69.dao;

import java.util.List;

import com.ispan.eeit69.model.Item;

public interface ItemDao {
	
	void resetItemTable();
	
	void save(Item item);
	
	void deleteById(Integer id);

	void update(Item item);
	
	Item findByItemId(String itemId);
	
	Item findById(Integer id);
	
	List<Item> findAll();
	
	boolean isPersist(Item item);

	void detach(Item item);
	
	int getTotalItemCount();
	
	List<Item> getItemSubset(int startIndex, int recordsPerPage);
	
    int countItemsByKeyword(String keyword);
    
    public List<Item> findItemsByKeyword(String keyword, int startIndex, int recordsPerPage);
	
}
