package com.ispan.eeit69.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.ItemCategory;
import com.ispan.eeit69.model.Review;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.ItemCategoryRepository;
import com.ispan.eeit69.repository.ItemsRepository;
import com.ispan.eeit69.repository.ReviewRepository;

@Controller
public class ItemController {

	private ItemsRepository itemRepository;


	private ItemCategoryRepository itemCategoryRepository;

	private ReviewRepository reviewRepository;


	
	public ItemController(ItemsRepository itemRepository, ItemCategoryRepository itemCategoryRepository,
			ReviewRepository reviewRepository) {
		super();
		this.itemRepository = itemRepository;
		this.itemCategoryRepository = itemCategoryRepository;
		this.reviewRepository = reviewRepository;

	}
	@GetMapping("/")
	public String home(Model model,HttpSession session) {
		
		List<Item> itemsByCategory1;
		List<Item> itemsByCategory2;
		itemsByCategory1 = itemRepository.findByItemStockLessThanOrderByItemStockAsc(50); // 熱門商品
		itemsByCategory2 = itemRepository.findByDiscountIsNotNullOrderByDiscountDesc(); // 優惠商品

		model.addAttribute("items1", itemsByCategory1.subList(0, 4));
        model.addAttribute("items2", itemsByCategory2.subList(0, 4));

		
		return "index";
	}

	@GetMapping("/ItemCategory")
	public String getGamesByCategory(Model model,
	        @RequestParam(name = "category", required = false) String categoryName) {
	    List<ItemCategory> allCategories = itemCategoryRepository.findAll();
	    model.addAttribute("itemCategories", allCategories);

	    if (categoryName != null && !categoryName.isEmpty()) {
	        List<Item> itemsByCategory;
	        if ("優惠商品".equals(categoryName)) {
	            itemsByCategory = itemRepository.findByDiscountIsNotNullOrderByDiscountDesc(); // 優惠商品
	        } else if ("熱門商品".equals(categoryName)) {
	            itemsByCategory = itemRepository.findByItemStockLessThanOrderByItemStockAsc(50); // 熱門商品
	        } else {
	            itemsByCategory = itemRepository.findByItemCategoryId_CategoryName(categoryName); // 其他類別
	        }
	        model.addAttribute("items", itemsByCategory);
	        model.addAttribute("categoryName", categoryName);
	    }
	    return "ItemCategory"; // 您的視圖名稱
	}

//	@GetMapping("/game/{id}")
//	public String hello2(Model model) {
//		List<ItemCategory> allCategories = itemCategoryRepository.findAll();
//        List<Review> review = itemsService.findAll();
//        model.addAttribute("itemCategories", allCategories);
//        model.addAttribute("review",review);
//        return "Game5";
//	
//	}

	@GetMapping("/Item/{id}")
	public String showGameDetails(@PathVariable int id, Model model,HttpSession session) {
		// 從資料庫中獲取相關的遊戲信息
		Item items = itemRepository.findById(id).orElse(null);
		if (items == null) {
			// 處理遊戲不存在的情況，例如返回一個404頁面
			return "errorPage";
		}
		
		User myUser=(User)session.getAttribute("myUser");
		String currentUserId = "-1";
		if (myUser != null) {
			currentUserId = String.valueOf(myUser.getId());
		}
		System.out.println(currentUserId);
		// 將所有獲取到的數據添加到 model 對象中
		model.addAttribute("currentUserId",currentUserId);
		model.addAttribute("newReview", new Review());
		model.addAttribute("items", items);
		return "Item1"; // 返回遊戲詳情的視圖名稱
	}

	@PostMapping("/Item/{id}")
	public String handleFormSubmission(@PathVariable int id, Review newReview, HttpSession session) {
		newReview.setId(null);
		// 獲取用戶名
		User myUser = (User) session.getAttribute("myUser");
		
		// 基本的數據驗證
		if (newReview.getRating() < 0 || newReview.getRating() > 5) {
			// 不合法的評分
			return "errorPage";
		}

		// 設置與 Items 的關聯
		Item items = itemRepository.findById(id).orElse(null);
		if (items != null) {
			newReview.setItemsId(items);

		} else {
			// Items 不存在-+
			return "errorPage";
		}
		if (myUser != null) {
			// 使用myUser的ID
			newReview.setUserId(myUser);
			System.out.println("Debug: myUser after setting = " + myUser);
		}

		// 保存 Review 對象
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		newReview.setCreationTime(ts);
//		System.out.println("Review ID before save: " + newReview.getId());  // 應該是 null 或未設置
		reviewRepository.save(newReview);
//		System.out.println("Review ID after save: " + newReview.getId());  // 應該是一個新的、自動生成的ID

		// 重定向到遊戲詳情頁
		return "redirect:/Item/" + id;
	}

	@PostMapping("/search")
	public ResponseEntity<Map<String, Object>> search(@RequestBody Map<String, String> payload) {
	    String query = payload.get("query"); // 從前端獲取搜尋詞
	    Map<String, Object> response = new HashMap<>();
	    
	    // 在這裡添加您的業務邏輯
	    Optional<Item> optionalItem = itemRepository.findByitemName(query);  // 假設findByName是您在ItemsRepository中定義的方法

	    if (optionalItem.isPresent()) {
	        // 找到商品，進行相應的操作（例如：返回商品的ID、名稱等）
	        Item foundItem = optionalItem.get();
	        response.put("success", true);
	        response.put("itemId", foundItem.getId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        // 未找到商品，設置success為false
	        response.put("success", false);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    

	}
	@GetMapping("/api/reviews")
    @ResponseBody
    public ResponseEntity<?> getReviews(
            @RequestParam(required = false) Integer gameId,
            @RequestParam(required = false) Integer star,
            Pageable pageable) {
        
        Page<Review> reviews;

        if (gameId != null) {
            Item item = itemRepository.findById(gameId).orElse(null);

            if (star == null || star == 6) {
                reviews = reviewRepository.findByItemsId(item, pageable);
            } else {
                reviews = reviewRepository.findByItemsIdAndRating(item, star, pageable);
            }
        } else {
            reviews = reviewRepository.findAll(pageable);
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Review review : reviews) {
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("id", review.getId());
            reviewMap.put("text", review.getReviewText());
            if (review.getUserId() != null) {
                reviewMap.put("username", review.getUserId().getUsername());
                reviewMap.put("userId", review.getUserId().getId());
                if (review.getUserId().getAvatar() != null) {
                    try {
                        reviewMap.put("avatar", review.getUserId().getAvatar().getDataUri());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    reviewMap.put("avatar", "");
                }
            } else {
                reviewMap.put("username", "匿名");
                reviewMap.put("userId", null);
                reviewMap.put("avatar", "");
            }
            reviewMap.put("rating", review.getRating());
            responseList.add(reviewMap);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", responseList);
        response.put("currentPage", reviews.getNumber());
        response.put("totalPages", reviews.getTotalPages());

        return ResponseEntity.ok(response);
    }
    
	@PutMapping("/reviews2/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review review) {

    	
        Optional<Review> existingReviewOpt = reviewRepository.findById(id);
        if (!existingReviewOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Review existingReview = existingReviewOpt.get();
        existingReview.setReviewText(review.getReviewText());
        existingReview.setRating(review.getRating());
        
        // 其他需要更新的字段...

        Review updatedReview = reviewRepository.save(existingReview);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        // 先檢查該評論是否存在

        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        reviewRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}




