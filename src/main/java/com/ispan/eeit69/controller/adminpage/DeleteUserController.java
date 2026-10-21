package com.ispan.eeit69.controller.adminpage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.service.UserService;

@Controller
@RequestMapping("/adminpages")
public class DeleteUserController extends AbstractController{
	
	Logger log = LoggerFactory.getLogger(DeleteItemController.class);
	
	UserService userService;
	
	public DeleteUserController(UserService userService) {
		this.userService = userService;
	}
	
	@DeleteMapping("/user/UserDelete/{id}")
	public String deleteItem(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			log.info("123456");
			userService.deleteById(id);
			ra.addFlashAttribute("message", "已刪除員工編號:" + empNo + "之紀錄");
			ra.addFlashAttribute("success", true);
			log.info("已刪除員工編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "已刪除員工編號:" + empNo + "之紀錄失敗" + e.getMessage());
			ra.addFlashAttribute("success", true);
			log.error("刪除員工編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/adminpages/user/queryUser";
	}

}
