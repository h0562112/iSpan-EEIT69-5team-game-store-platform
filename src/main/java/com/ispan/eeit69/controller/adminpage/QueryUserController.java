package com.ispan.eeit69.controller.adminpage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.eeit69.model.User;
import com.ispan.eeit69.service.UserService;

@Controller
@RequestMapping("/adminpages")
public class QueryUserController extends AbstractController {
	
    int recordsPerPage = 5; //每頁筆數
	
	Logger log = LoggerFactory.getLogger(QueryUserController.class);
	
	UserService userService;

	public QueryUserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user/queryUser")
    public String queryUser(@RequestParam(defaultValue = "1") long page, Model model) {
		int pageInt = (int) page;
        long totalRecords = userService.getTotalUserCount();
        long totalPages = (long) Math.ceil((double) totalRecords / recordsPerPage);

        if (pageInt < 1) {
        	pageInt = 1;
        } else if (pageInt > totalPages) {
        	pageInt = (int)totalPages;
        }

        int startIndex = (pageInt - 1) * recordsPerPage;
        Page<User> userList = userService.getUserSubset(startIndex, recordsPerPage);

        model.addAttribute("userList", userList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
        model.addAttribute("currentPage", pageInt);

        return "/adminpages/users"; 
    }
	
	
	@GetMapping("/user/queryUserByKeyword")
	public String queryUserByKeyword(@RequestParam(defaultValue = "1") long page, 
	                                  @RequestParam(required = false) String keyword, 
	                                  Model model) {
	    long totalRecords;
	    int pageInt = (int) page;
	    Page<User> userList;

	    if (keyword != null && !keyword.isEmpty()) {
	        // 如果提供了關鍵字，則根據關鍵字查詢物品
	        totalRecords = userService.getTotalUserCountByKeyword(keyword);
	        userList = userService.searchUsersByKeyword(keyword, pageInt, recordsPerPage);
	    } else {
	        // 如果未提供關鍵字，則獲取所有物品
	        totalRecords = userService.getTotalUserCount();
	        userList = userService.getUserSubset((pageInt - 1) * recordsPerPage, recordsPerPage);
	    }

	    long totalPages = (long) Math.ceil((double) totalRecords / recordsPerPage);

	    if (pageInt < 1) {
	    	pageInt = 1;
	    } else if (pageInt > totalPages) {
	    	pageInt = (int)totalPages;
	    }

	    int startIndex = (pageInt - 1) * recordsPerPage;

	    model.addAttribute("userList", userList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startIndex", startIndex);
	    model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
	    model.addAttribute("currentPage", pageInt);
	    model.addAttribute("keyword", keyword);

	    return "/adminpages/users";
	}
}
