package com.ispan.eeit69.controller.adminpage;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.ItemCategory;
import com.ispan.eeit69.repository.ItemCategoryRepository;
import com.ispan.eeit69.service.ItemService;
import com.ispan.eeit69.validator.ItemValidator;

@Controller
@RequestMapping("/adminpages")
@SessionAttributes("previous_item_id")
public class UpdateItemController extends AbstractController{
	
	Logger log = LoggerFactory.getLogger(UpdateItemController.class);
	
	ItemService itemService;
	
	@Autowired
	ItemCategoryRepository itemCategoryRepository;
	
	ItemValidator itemValidator;
	
//	@Autowired
	public UpdateItemController(ItemService itemService, ItemValidator itemValidator) {
		super();
		this.itemService = itemService;
		this.itemValidator = itemValidator;
	}
	
	
	 @GetMapping("/item/findById/{id}")
	 public String sendEmptyForm(Model model) {  
	  List<ItemCategory> itemCategory = itemCategoryRepository.findAll();	       
	  model.addAttribute("ItemCategory",itemCategory);
	  log.info("送出可供前端使用者修改的表單");
	  return "/adminpages/updateitem";
	 }

	
	
	@PutMapping("/item/editItem/{id}")
	public String updateItem(@ModelAttribute Item item, BindingResult result,
			@RequestParam String previous_itemId, Model model, RedirectAttributes ra)
			throws SerialException, SQLException {
		// 對使用者輸入之資料進行驗證
		itemValidator.validate(item, result);
		// 如果修改後的資料有錯誤
		if (result.hasErrors()) {
			log.warn("前端使用者送回的表單含有錯誤資料");
			log.warn("-------------------------------------");
			// -------------------------------------
			// 下列迴圈僅為了找出顯示我們需要之錯誤訊息所需的key:
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				log.warn(error.toString());
			}
			log.warn("-------------------------------------");
			// -------------------------------------
			if (!result.hasFieldErrors("filename")) {
				// 為了能正確顯示圖片
				model.addAttribute("image", item.getImage());
				model.addAttribute("filename", item.getFilename());
			}
			// 導向原來修改資料的頁面updateEmployee.jsp
			// 重要，重要，重要: updateEmployee.jsp 的<%@ page ... %>一定加上 isErrorPage='true'
			return "/adminpages/updateitem";
		}
		// 如果修改員工編號，需要檢查修改後的員工編號是否已經存在
		if (!previous_itemId.equals(item.getItemId())) {
			log.warn("使用者修改了商品編號，原來商品編號: " + previous_itemId + ", 新的商品編號: " + item.getItemId());
			// employeeService.existsByEmployeeId(employee)需要先檢查 employee 物件是否為
			// persistent entity。如果是，要先移除(detach)它，因為JPA在執行 JPQL (對應 HQL)
			// 前會先進行 entityManager.flush(), 此舉會將含有重複的employeeId寫入表格中而造成錯誤
			if (itemService.existsByItemId(item)) {
				log.warn("新的商品編號已經存在，系統不接受");
				result.rejectValue("itemId", "item.itemId.exist.error", "商品編號已存在，請更換新的商品編號");
				model.addAttribute("image", item.getImage());
				model.addAttribute("filename", item.getFilename());
				//
				return "/adminpages/updateitem";
			}
		}
		// 將圖片資料(data url)轉換為 Clob
		char[] c = item.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		item.setItempicture(clob);
		
		log.info(item.toString()); //debug employee 值沒有變
		
		itemService.update(item);
		ra.addFlashAttribute("message", "資料修改成功");
		ra.addFlashAttribute("success", true);
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("修改商品資料已經完成");
		return "redirect:/adminpages/item/queryItem";
	}
	
	@ModelAttribute
	public Item getMember(@PathVariable Integer id, Model model) {
		Item item = itemService.findById(id);
		model.addAttribute("previous_item_id", item.getItemId());
		log.info("依照傳入的主鍵值:" + id + "讀取對應的紀錄");
		return item;
	}

	

}
