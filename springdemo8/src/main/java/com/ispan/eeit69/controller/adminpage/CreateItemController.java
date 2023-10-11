package com.ispan.eeit69.controller.adminpage;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.ItemCategory;
import com.ispan.eeit69.repository.ItemCategoryRepository;
import com.ispan.eeit69.service.ItemService;
import com.ispan.eeit69.utils.SystemService;
import com.ispan.eeit69.validator.ItemValidator;

@Controller
@RequestMapping("/adminpages")
public class CreateItemController extends AbstractController {
	
	Logger log = LoggerFactory.getLogger(CreateItemController.class);
	
	ItemService itemService;
	@Autowired
	ItemCategoryRepository itemCategoryRepository;
	ItemValidator itemValidator;
	
//	@Autowired
    public CreateItemController(ItemService itemService, ItemValidator itemValidator,ItemCategoryRepository itemCategoryRepository) {
		this.itemService = itemService;
		this.itemValidator = itemValidator;
		this.itemCategoryRepository = itemCategoryRepository;
	}
	
	@GetMapping("/item/CreateItemForm")
	public String sendEmptyForm(Model model) {
		Item item = new Item();
		// 表單之<form:form 標籤的 modelAttribute屬性需要下列敘述儲存的屬性物件；
		model.addAttribute(item);  // 注意：使用預設的識別字串
		List<ItemCategory> ItemCategory= itemCategoryRepository.findAll();
		model.addAttribute("ItemCategory",ItemCategory);
		log.info("送出可供前端使用者新增的空白表單");
		
		return "/adminpages/createitem";     
	}
	
	@PostMapping("/item/insertItem")
	public String saveItem(@ModelAttribute Item item, BindingResult result, Model model,
			RedirectAttributes ra) throws SerialException, SQLException {

		itemValidator.validate(item, result);
		if (result.hasErrors()) {
			log.warn("前端使用者送回的表單含有錯誤資料");
		    for (FieldError error : result.getFieldErrors()) {
		        log.warn("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
		    }
			log.warn("-------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : errors) {
//				log.warn(error.toString());
//			}
			// -------------------------------------
			// 如果使用者挑選圖片，if敘述的條件會是true
			if (!result.hasFieldErrors("filename")) {
				// 將圖片與圖片檔名的取出存入Model內以方便於驗證失敗時導回原輸入畫面時仍能顯示原來的圖片
				model.addAttribute("image", item.getImage());
				model.addAttribute("filename", item.getFilename());
			}
			return "/adminpages/createitem";
		}

		if (itemService.existsByItemId(item)) {
			log.warn("提供的商品編號已存在: " + item.getItemId());
			result.rejectValue("itemId", "item.itemId.exist.error", "商品編號已存在，請更換新的商品編號");
			model.addAttribute("image", item.getImage());
			model.addAttribute("filename", item.getFilename());
			return "/adminpages/createitem";
		}

		char[] c = item.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		item.setItempicture(clob);
		itemService.save(item);
		// 要將圖檔寫入Server端的資料夾
		File imageMainFolder = new File(SystemService.EMPLOYEE_IMAGE_FILE_FOLDER);
		String fileExt = ".txt";
		File outFile = new File(imageMainFolder, "Item_" + item.getId() + fileExt);
		try (PrintWriter pw = new PrintWriter(outFile);) {
			pw.print(item.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ra.addFlashAttribute("message", "資料新增成功");
		ra.addFlashAttribute("success", true);
//		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("新增商品資料已經完成");
		return "redirect:/adminpages/item/queryItem";
	}
	
	@ModelAttribute
	public Item beforeSave() {
		Item item = new Item();
		// 於新增員工資料前加入系統產生的資料
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		item.setCreateTime(ts);
		return item;
	}
	
	
	
	

}
