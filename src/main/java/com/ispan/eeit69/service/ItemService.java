package com.ispan.eeit69.service;

import java.util.List;

import com.ispan.eeit69.model.Item;

public interface ItemService {
	
	void resetItemTable();
	
	void save(Item item);
	
	void deleteById(Integer id);
	
	void update(Item item);
	
	Item findByItemId(String itemId);
	
	boolean existsByItemId(Item item);
	
	Item findById(Integer id);
	
	List<Item> findAll();
	
	boolean isPersist(Item item);
	
	void detach(Item item);
	
	int getTotalItemCount();
	
	List<Item> getItemSubset(int startIndex, int recordsPerPage);
	
	int getTotalItemCountByKeyword(String keyword);
	
	List<Item> searchItemsByKeyword(String keyword, int page, int recordsPerPage);
	

}
