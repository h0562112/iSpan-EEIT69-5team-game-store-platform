package com.ispan.eeit69.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.service.ItemService;

@Component
public class ItemValidator implements Validator {
	
	ItemService itemService;

//	@Autowired
	ItemValidator(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Item.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "itemId", "item.itemId.empty.error", "必須輸入商品編號"); 

		Item item = (Item)target;
		
		if (item.getImage() == null || item.getImage().length() == 0) {
			if (item.getFilename().length() == 0) {
				errors.rejectValue("filename", "item.image.empty.error", "必須挑選照片");				
			}
		}
		
	}
	
	
}
