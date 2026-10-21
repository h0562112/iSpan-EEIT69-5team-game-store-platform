package com.ispan.eeit69.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit69.repository.NewsRepository;

/** 最新消息（訪客也能看） */
@Controller
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/news")
	public String news(Model model) {
		model.addAttribute("newsList", newsRepository.findAllByOrderByCreateTimeDescIdDesc());
		return "news";
	}
}
