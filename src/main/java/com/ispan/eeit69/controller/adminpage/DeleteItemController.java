package com.ispan.eeit69.controller.adminpage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.service.ItemService;

@Controller
@RequestMapping("/adminpages")
public class DeleteItemController extends AbstractController{
	
	Logger log = LoggerFactory.getLogger(DeleteItemController.class);
	
	ItemService itemService;

	public DeleteItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@DeleteMapping("/item/ItemDelete/{id}")
	public String deleteItem(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			log.info("123456");
			itemService.deleteById(id);
			ra.addFlashAttribute("message", "已刪除商品編號:" + empNo + "之紀錄");
			ra.addFlashAttribute("success", true);
			log.info("已刪除商品編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "已刪除商品編號:" + empNo + "之紀錄失敗" + e.getMessage());
			ra.addFlashAttribute("success", true);
			log.error("刪除商品編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/adminpages/item/queryItem";
	}
	
	

}
