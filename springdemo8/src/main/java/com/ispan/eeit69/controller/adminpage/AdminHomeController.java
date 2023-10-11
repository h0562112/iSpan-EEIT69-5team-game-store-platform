package com.ispan.eeit69.controller.adminpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/adminpages")
public class AdminHomeController {
	
	@GetMapping("/adminindex")
	public String home() {
		return "/adminpages/index";
	}
	
}
