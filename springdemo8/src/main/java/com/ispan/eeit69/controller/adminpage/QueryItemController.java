package com.ispan.eeit69.controller.adminpage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.service.ItemService;

@Controller
@RequestMapping("/adminpages")
public class QueryItemController extends AbstractController {
	
	int recordsPerPage = 5; //每頁筆數
	
	Logger log = LoggerFactory.getLogger(QueryItemController.class);
	
	ItemService itemService;

	public QueryItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	
	
	
	@GetMapping("/item/queryItem")
    public String queryItem(@RequestParam(defaultValue = "1") int page, Model model) {
        int totalRecords = itemService.getTotalItemCount();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startIndex = (page - 1) * recordsPerPage;
        List<Item> itemList = itemService.getItemSubset(startIndex, recordsPerPage);

        model.addAttribute("itemList", itemList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalRecords", totalRecords);

        return "/adminpages/items"; 
    }
	
	
	
	
	@GetMapping("/item/queryItemByKeyword")
	public String queryItemByKeyword(@RequestParam(defaultValue = "1") int page, 
	                                  @RequestParam(required = false) String keyword, 
	                                  Model model) {
	    int totalRecords;
	    List<Item> itemList;

	    if (keyword != null && !keyword.isEmpty()) {
	        // 如果提供了關鍵字，則根據關鍵字查詢物品
	        totalRecords = itemService.getTotalItemCountByKeyword(keyword);
	        itemList = itemService.searchItemsByKeyword(keyword, page, recordsPerPage);
	    } else {
	        // 如果未提供關鍵字，則獲取所有物品
	        totalRecords = itemService.getTotalItemCount();
	        itemList = itemService.getItemSubset((page - 1) * recordsPerPage, recordsPerPage);
	    }

	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

	    if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }

	    int startIndex = (page - 1) * recordsPerPage;

	    model.addAttribute("itemList", itemList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startIndex", startIndex);
	    model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
	    model.addAttribute("currentPage", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("totalRecords", totalRecords);

	    return "/adminpages/items";
	}
	

	
}
