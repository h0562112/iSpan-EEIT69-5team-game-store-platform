package com.ispan.eeit69.controller.adminpage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.service.EmployeeService;

@Controller
@RequestMapping("/adminpages")
public class QueryEmployeeController extends AbstractController{
	
	int recordsPerPage = 5; //每頁筆數

	Logger log = LoggerFactory.getLogger(QueryEmployeeController.class);
	
	EmployeeService  employeeService;
	
//	@Autowired
	public QueryEmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
    // 	查詢所有的員工資料             
//	@GetMapping("/employee/queryEmployee")
//	public String findAll(Model model) {
//		List<Employee>  employees = employeeService.findAll();
//		model.addAttribute(employees);   // 使用預設的識別字串 "employeeList"
//		log.info("送出所有員工資料供showAllEmployees.jsp顯示");
//		// 下一行對應  /WEB-INF/views/showAllEmployees.jsp
//		return "/adminpages/employees";    
//	}
	
	
	@GetMapping("/employee/queryEmployee")
    public String queryEmployee(@RequestParam(defaultValue = "1") int page, Model model) {
        int totalRecords = employeeService.getTotalEmployeeCount();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startIndex = (page - 1) * recordsPerPage;
        List<Employee> employeeList = employeeService.getEmployeeSubset(startIndex, recordsPerPage);

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalRecords", totalRecords);

        return "/adminpages/employees"; 
    }
	
	
	@GetMapping("/employee/queryEmployeeByKeyword")
	public String queryItemByKeyword(@RequestParam(defaultValue = "1") int page, 
	                                  @RequestParam(required = false) String keyword, 
	                                  Model model) {
	    int totalRecords;
	    List<Employee> employeeList;

	    if (keyword != null && !keyword.isEmpty()) {
	        // 如果提供了關鍵字，則根據關鍵字查詢物品
	        totalRecords = employeeService.getTotalEmployeeCountByKeyword(keyword);
	        employeeList = employeeService.searchEmployeesByKeyword(keyword, page, recordsPerPage);
	    } else {
	        // 如果未提供關鍵字，則獲取所有物品
	        totalRecords = employeeService.getTotalEmployeeCount();
	        employeeList = employeeService.getEmployeeSubset((page - 1) * recordsPerPage, recordsPerPage);
	    }

	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

	    if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }

	    int startIndex = (page - 1) * recordsPerPage;

	    model.addAttribute("employeeList", employeeList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startIndex", startIndex);
	    model.addAttribute("endIndex", Math.min(startIndex + recordsPerPage - 1, totalRecords - 1));
	    model.addAttribute("currentPage", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("totalRecords", totalRecords);

	    return "/adminpages/employees";
	}
	
}
