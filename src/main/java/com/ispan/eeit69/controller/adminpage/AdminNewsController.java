package com.ispan.eeit69.controller.adminpage;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.News;
import com.ispan.eeit69.repository.NewsRepository;

/** 後台 - 最新消息 */
@Controller
@RequestMapping("/adminpages/news")
public class AdminNewsController {

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("newsList", newsRepository.findAllByOrderByCreateTimeDescIdDesc());
		return "adminpages/news";
	}

	@PostMapping("/create")
	public String create(@RequestParam String title, @RequestParam String content, RedirectAttributes ra) {
		title = title.trim();
		if (title.isEmpty() || title.length() > 255) {
			ra.addFlashAttribute("error", "標題不可空白，且最多 255 個字");
			return "redirect:/adminpages/news";
		}
		News news = new News();
		news.setTitle(title);
		news.setContent(content.trim());
		news.setCreateTime(new Timestamp(System.currentTimeMillis()));
		newsRepository.save(news);
		ra.addFlashAttribute("message", "已發布：" + title);
		return "redirect:/adminpages/news";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Integer id, RedirectAttributes ra) {
		if (newsRepository.existsById(id)) {
			newsRepository.deleteById(id);
			ra.addFlashAttribute("message", "已刪除");
		}
		return "redirect:/adminpages/news";
	}
}
