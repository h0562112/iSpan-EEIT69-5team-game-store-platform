package com.ispan.eeit69.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.dao.ItemDao;
import com.ispan.eeit69.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@PersistenceContext
	EntityManager  entityManager;

	@Override
	public void resetItemTable() {
		throw new RuntimeException("本系統未提供此功能");

	}

	@Override
	public void save(Item item) {
		entityManager.persist(item);

	}

	@Override
	public void deleteById(Integer id) {
		String hql = "DELETE FROM Item e WHERE e.id = :id";
		entityManager.createQuery(hql)
		             .setParameter("id", id)
		             .executeUpdate();

	}

	@Override
	public void update(Item item) {
		entityManager.merge(item);

	}
	
	@Override
	public Item findByItemId(String itemId) {
		Item result = null;
		String hql = "FROM Item WHERE itemId = :eid";
		List<Item>  items = entityManager.createQuery(hql, Item.class)
			                           			 .setParameter("eid", itemId)
		                           			 	 .getResultList();
		if (items.size() > 0) {
			result = items.get(0);
		}
		return result;
	}

	@Override
	public Item findById(Integer id) {
		Item result = entityManager.find(Item.class, id);
		return result;
	}

	@Override
	public List<Item> findAll() {
		String hql = "FROM Item";
		List<Item>  Items = entityManager.createQuery(hql, Item.class)
				                                 .getResultList();
		return Items;
	}

	@Override
	public boolean isPersist(Item item) {
		// 判斷參數employee是否為entityManager控管的永續物件
				boolean ans = entityManager.contains(item); 
				if (ans)
				   return true;
				else 
					return false;
	}

	@Override
	public void detach(Item item) {
		entityManager.detach(item);

	}

	@Override
	public int getTotalItemCount() {
		Query query = entityManager.createQuery("SELECT COUNT(e) FROM Item e");
        return ((Number) query.getSingleResult()).intValue();
	}

	@Override
	public List<Item> getItemSubset(int startIndex, int recordsPerPage) {
		Query query = entityManager.createQuery("SELECT e FROM Item e");
        query.setFirstResult(startIndex);
        query.setMaxResults(recordsPerPage);
        List<Item> resultList = query.getResultList(); 
        return  resultList; 
	}
	
	@Override
    public int countItemsByKeyword(String keyword) {
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM Item e WHERE e.itemName LIKE :keyword")
            .setParameter("keyword", "%" + keyword + "%");
        return ((Number) query.getSingleResult()).intValue();
    }
	
	@Override
    public List<Item> findItemsByKeyword(String keyword, int startIndex, int recordsPerPage) {
        Query query = entityManager.createQuery("SELECT e FROM Item e WHERE e.itemName LIKE :keyword")
            .setParameter("keyword", "%" + keyword + "%")
            .setFirstResult(startIndex)
            .setMaxResults(recordsPerPage);
        return query.getResultList();
    }
	

}
