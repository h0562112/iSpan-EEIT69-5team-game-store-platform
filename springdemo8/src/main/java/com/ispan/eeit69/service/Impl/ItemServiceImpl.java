package com.ispan.eeit69.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.dao.ItemDao;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	ItemDao itemDao;
	
//	@Autowired
	public ItemServiceImpl(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public void resetItemTable() {
		itemDao.resetItemTable();

	}

	@Override
	public void save(Item item) {
		itemDao.save(item);

	}

	@Override
	public void deleteById(Integer id) {
		itemDao.deleteById(id);

	}

	@Override
	public void update(Item item) {
		itemDao.update(item);

	}

	@Override
	public Item findByItemId(String itemId) {
		Item item = itemDao.findByItemId(itemId);
		return item;

	}

	@Override
	public boolean existsByItemId(Item item) {
		if (itemDao.isPersist(item)) {
			itemDao.detach(item);
		}
		Item item1 = findByItemId(item.getItemId());
		return item1 != null;
	}

	@Override
	public Item findById(Integer id) {
		return itemDao.findById(id);
	}

	@Override
	public List<Item> findAll() {
		return itemDao.findAll();
	}

	@Override
	public boolean isPersist(Item item) {
		boolean ans = itemDao.isPersist(item);
		return ans;
	}

	@Override
	public void detach(Item item) {
		itemDao.detach(item);

	}

	@Override
	public int getTotalItemCount() {
		return itemDao.getTotalItemCount();
	}

	@Override
	public List<Item> getItemSubset(int startIndex, int recordsPerPage) {
		return itemDao.getItemSubset(startIndex, recordsPerPage);
	}
	
    @Override
    public int getTotalItemCountByKeyword(String keyword) {
        return itemDao.countItemsByKeyword(keyword);
    }
    
    @Override
    public List<Item> searchItemsByKeyword(String keyword, int page, int recordsPerPage) {
        int startIndex = (page - 1) * recordsPerPage;
        return itemDao.findItemsByKeyword(keyword, startIndex, recordsPerPage);
    }

}
