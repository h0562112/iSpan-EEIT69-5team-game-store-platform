package com.ispan.eeit69.controller.membercontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadResourceController {
	
//	@Value("classpath:static/text/employeesBIG5.txt")
	@Value("file:C:\\_javaee\\spring-workspaceA\\springdemo\\src\\main\\resources\\static\\text\\employeesBIG5.txt")
	Resource  cpr;
	
	@Value("url:http://www.space-fox.com/wallpapers/celebs/penelope-cruz/penelope_cruz_1.jpg")
	Resource  url;
	
//	@GetMapping("/ReadStaticFileBig5")
//	public String readStaticFileBig5(Model model) {
//		List<Employee> employees = new ArrayList<>();
//		try {
//			//ClassPathResource  cpr = new ClassPathResource("/static/text/employees.txt");
//			InputStream is = cpr.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is, "BIG5");
//			BufferedReader br = new BufferedReader(isr);
//			String line = "";
//			while ((line = br.readLine()) != null) {
//			    String[] sa = line.split(",");
//			    Employee emp = new Employee(null, sa[0], sa[1],  sa[3], null);
//			    employees.add(emp);
//
//			}
//			model.addAttribute(employees);
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "showEmployeeDataFromFile";
//	}
	@GetMapping("/readResourceFromWeb")
	public String readResourceFromWeb(Model model) {
		File folder = new File("c:\\images\\202308\\eeit69");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String filename = url.getFilename();
		
		System.out.println("filename=" + filename);
		File output = new File(folder, filename);
		try(
			InputStream is = url.getInputStream();
			FileOutputStream fos = new FileOutputStream(output);
		) {
			byte[] b = new byte[8192];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			model.addAttribute("message", filename + "讀取完畢...");
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", filename + "讀取失敗..." + e.getMessage());
		}
		
		return "showEmployeeDataFromFile";
	}
	
	
//	@GetMapping("/ReadStaticFile")
//	public String readStaticFile(Model model) {
//		List<Employee> employees = new ArrayList<>();
//		try {
//			ClassPathResource  cpr = new ClassPathResource("/static/text/employees.txt");
//			InputStream is = cpr.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String line = "";
//			while ((line = br.readLine()) != null) {
//			    String[] sa = line.split(",");
//			    Employee emp = new Employee(null, sa[0], sa[1], sa[3], null);
//			    employees.add(emp);
//
//			}
//			model.addAttribute(employees);
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "showEmployeeDataFromFile";
//	}
	
	

}
