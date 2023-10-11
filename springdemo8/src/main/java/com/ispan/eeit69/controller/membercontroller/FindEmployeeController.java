package com.ispan.eeit69.controller.membercontroller;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.service.member.AvatarService;
import com.ispan.eeit69.service.member.memberService;

@Controller
public class FindEmployeeController {
	
	
	AvatarService avatarService;
	memberService  employeeService;
	BCryptPasswordEncoder bcryptPasswordEncoder;
//	@Autowired
	public FindEmployeeController(memberService employeeService,AvatarService avatarService,BCryptPasswordEncoder bcryptPasswordEncoder) {
		this.employeeService = employeeService;
		this.avatarService = avatarService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}
	
	
	@GetMapping("/55667788")
	public String findByfriends(){
		return "member-profile/friend-list-copy";
	}
	
	@GetMapping("/test")
	public String findByfriends(Model model, 
			@RequestParam Integer id) {
		List<Employee>  employees = employeeService.findAll();
		Employee employee = employeeService.findById(id);
		model.addAttribute(employees);
		model.addAttribute(employee);   
		return "websock";
	   
//		 return "New-friend-list";
		 
	}
	
	@GetMapping("/findEmployee.do")
	public String findById(Model model, @RequestParam Integer id, Integer userId) {
	    User user = employeeService.findByUserId(id);
	    Avatar image = employeeService.findAvatarByUserId(userId);

	    if (image != null && image.getPicture() != null) {
	        try {
	            Clob itemPictureClob = image.getPicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String itemPictureBase64 = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
	            model.addAttribute("itemPictureBase64",itemPictureBase64);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }

	    model.addAttribute("user", user);

	    return "member-profile/member-profile";
	}

	@GetMapping("/updatep_Profile")
	public String updatep_Profile(Model model, 
			@RequestParam Integer id,Integer userId) {
		User user = employeeService.findByUserId(id);
		Avatar image = employeeService.findAvatarByUserId(userId);
		
		if (image != null && image.getPicture() != null) {
	        try {
	            Clob itemPictureClob = image.getPicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String itemPictureBase64 = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
	            model.addAttribute("itemPictureBase64", itemPictureBase64);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }
		
		model.addAttribute(user);
//		return "updateEmployee";
		return "member-profile/member_Update";
	}
	
	@GetMapping("/findgameid")
	public String findgameid(Model model, 
			@RequestParam Integer id) {
		Item item = employeeService.findBygameId(id);
		
		if (item != null && item.getItempicture() != null) {
	        try {
	            Clob itemPictureClob = item.getItempicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String itemPictureBase64 = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
	            model.addAttribute("itemPictureBase64", itemPictureBase64);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }
		
	    model.addAttribute("item", item); // 将商品信息添加到模型中
	    
	    System.out.println(item);
	    return "member-profile/game-page"; // 修改为您的JSP页面名称
	}

	@GetMapping("/newFriends")
	public String findByfriendss(Model model, 
			@RequestParam Integer id) {
//		List<User>  user = employeeService.findUserAll();
//		List<Employee>  employees = employeeService.findAll();
//		Employee employee = employeeService.findById(id);
		List<Object[]> user = employeeService.findByuserAllavatar();
		List<Object[]> friendsList = employeeService.findByfriendsidNew(id);
		User employee = employeeService.findByUserId(id);
		Avatar image = employeeService.findAvatarByUserId(id);
		if (image != null && image.getPicture() != null) {
	        try {
	            Clob itemPictureClob = image.getPicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String myimage = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
	            System.out.println("我要看圖片"+myimage);
	            model.addAttribute("myimage", myimage);
//	            System.out.println("我要看圖片"+senderUserimage);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }
		
//		System.out.println(employees);
		System.out.println("我是ID:"+employee);
		System.out.println("我是全部:"+user);
		System.out.println("我是好友清單:"+friendsList);
		
//		System.out.println("我是清單"+friendsList);
		model.addAttribute("user",user);
		model.addAttribute("employee",employee);
//		model.addAttribute(employee);
		model.addAttribute("friendsList", friendsList);
		return "member-profile/new_friend-list";
//		return "friend-list";
	}
	
	
	
	@GetMapping("/findfriendsid")
	public String findByfriendsid(Model model, @RequestParam Integer id) {
	    List<Object[]> friendsList = employeeService.findByfriendsid(id);
	    List<Object[]> friendsListnew = employeeService.findByfriendsidNew(id);
//	    List<Employee>  employees = employeeService.findAll();
//		Employee employee = employeeService.findById(id);
		User employee = employeeService.findByUserId(id);
		
		 Avatar image = employeeService.findAvatarByUserId(id);

		    if (image != null && image.getPicture() != null) {
		        try {
		            Clob itemPictureClob = image.getPicture();
		            // 直接從 Clob 讀取字符數據，並轉換為 String
		            Reader reader = itemPictureClob.getCharacterStream();
		            char[] buffer = new char[(int) itemPictureClob.length()];
		            reader.read(buffer);
		            String itemPictureBase64 = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

		            // 將 Base64 編碼的圖片數據添加到 Model 中
		            model.addAttribute("itemPictureBase64", itemPictureBase64);
		            System.out.println("我的圖片"+itemPictureBase64);
		        } catch (SQLException | IOException e) {
		            // 處理異常
		            e.printStackTrace();
		        }
		    }
		    
		   
		    
		
		System.out.println(employee);
		model.addAttribute("employee",employee);		
		model.addAttribute("friendsListnew", friendsListnew);
		model.addAttribute("friendsList", friendsList);
		System.out.println("好友名單"+friendsList);
//		model.addAttribute(employees);
//		model.addAttribute(employee);   
	    // 根据你的需求，你可以添加其他属性到模型中	
//		return "updateEmployee";
//		return "member-profile-ame-library-friend-list";
		return "member-profile/friend-list";
	}
	
	
	@GetMapping("/findPlayergameid")
	public String findPlayergameid(Model model, @RequestParam Integer id) {
	    List<Object[]> PlayergameList = employeeService.findByPlayerGameId(id);
	    model.addAttribute("PlayergameList", PlayergameList);
	    // 根据你的需求，你可以添加其他属性到模型中	
//		return "updateEmployee";
//		return "member-profile-ame-library-friend-list";
	   
		return "member-profile/game-library";
	}
	

	 @GetMapping("/findByfriendmessage")
	 public String findByfriendmessage(Model model, @RequestParam Integer senderUserid, Integer receiverUserid) {
	     List<FriendsMessages> message = employeeService.findByfriendmessages(senderUserid, receiverUserid);
//	     List<Avatar> avatar = employeeService.findAvatarAll();
	     
//	     System.out.println("我想要看"+avatar);
	     
	     
	Avatar receiverimage = employeeService.findAvatarByUserId(senderUserid);
	Avatar senderimage = employeeService.findAvatarByUserId(receiverUserid);
		if (receiverimage != null && receiverimage.getPicture() != null) {
	        try {
	            Clob itemPictureClob = receiverimage.getPicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String receiverUserimage = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
	            System.out.println("我要看圖片"+receiverUserimage);
	            model.addAttribute("receiverUserimage", receiverUserimage);
//	            System.out.println("我要看圖片"+senderUserimage);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }
		
		if (senderimage != null && senderimage.getPicture() != null) {
	        try {
	            Clob itemPictureClob = senderimage.getPicture();
	            // 直接從 Clob 讀取字符數據，並轉換為 String
	            Reader reader = itemPictureClob.getCharacterStream();
	            char[] buffer = new char[(int) itemPictureClob.length()];
	            reader.read(buffer);
	            String senderUserimage = new String(buffer);  // 這裡，我們直接將讀取的字符數據用作 Base64 編碼的字符串

	            // 將 Base64 編碼的圖片數據添加到 Model 中
//	            System.out.println("我要看圖片"+senderUserimage);
	            model.addAttribute("senderUserimage", senderUserimage);
//	            System.out.println("我要看圖片"+senderUserimage);

	        } catch (SQLException | IOException e) {
	            // 處理異常
	            e.printStackTrace();
	        }
	    }
	     
	     
	     // 将消息数据添加到模型
//	     model.addAttribute("avatar", avatar);
	     model.addAttribute("message", message);
	     model.addAttribute("senderUserid", senderUserid);
	     model.addAttribute("receiverUserid", receiverUserid);
	     // 返回视图名称，你可以在视图中显示查找到的员工信息
	     return "member-profile/messagePage";
	 }
			
//	@PostMapping("/updateEmployee.do.old")
//	public String updateEmployee(@ModelAttribute("preEmployee") Employee updatedEmployee) throws SerialException, SQLException {
//	    // 获取原始员工信息
//	    Employee existingEmployee = employeeService.findById(updatedEmployee.getId());
//	    
//	    if (existingEmployee != null) {
//	        // 更新员工信息
//	        existingEmployee.setName(updatedEmployee.getName());
//	        existingEmployee.setAccount(updatedEmployee.getAccount());
//	        existingEmployee.setEmail(updatedEmployee.getEmail());
//	        existingEmployee.setPassword(updatedEmployee.getPassword());
//	        
//	        // 检查是否有新的图像数据
//	        String imageUri = updatedEmployee.getImage();
//	        if (imageUri != null && !imageUri.isEmpty()) {
//	            // 使用 split 方法分割字符串
//	            String[] parts = imageUri.split(",");
//	            if (parts.length >= 3) {  // 确保有足够的元素
//	                // 合并 parts[1] 和 parts[2]
//	                String base64Image = parts[1] + "," + parts[2];
//	                // 转换为 Clob
//	                Clob imageClob = new SerialClob(base64Image.toCharArray());
//	                // 设置到 existingEmployee 对象中
//	                existingEmployee.setPicture(imageClob);
//	            }
//	        }
//	        
//	        // 调用更新方法
//	        employeeService.update(existingEmployee);
//	    }
//	    
//	    return "redirect:/findEmployee.do?id=" + updatedEmployee.getId(); // 重定向到员工列表页面
//	}
		
	@PostMapping("/updateEmployee.do")
	public String updateEmployee(@ModelAttribute User updatedUser, @RequestParam("file") MultipartFile file) throws SQLException, IOException {
	    // 获取原始员工信息
	    System.out.println("更新用户信息:" + updatedUser);

	    User existingUser = employeeService.findByUserId(updatedUser.getId());

	    if (existingUser != null) {
	        // 更新员工信息
	    	String sw = bcryptPasswordEncoder.encode(updatedUser.getPassword());
	    	
	    	existingUser.setPassword(sw);
	        existingUser.setUsername(updatedUser.getUsername());
	        existingUser.setEmail(updatedUser.getEmail());
	        // 更新用户信息
	        employeeService.Usertableupdate(existingUser);
	        System.out.println("这里是:" + file);

	        if (!file.isEmpty()) {
	            // 转换 MultipartFile 到 byte[]
	            byte[] bytes = file.getBytes();
	            // 使用 Base64 编码转换 byte[] 到 String
	            String base64Encoded = Base64.getEncoder().encodeToString(bytes);
	            // 创建 Clob 对象
	            Clob imageClob = new SerialClob(base64Encoded.toCharArray());

	            // 创建或获取 Avatar
	            Avatar avatar = avatarService.findAvatarByUserId(updatedUser.getId());
	            if (avatar == null) {
	                avatar = new Avatar();
	                avatar.setUser(existingUser);
	            }
	            avatar.setPicture(imageClob);

	            // 保存 Avatar
	            avatarService.saveOrUpdateAvatar(avatar);
	        }
	    }
	    return "redirect:/findEmployee.do?id=" + updatedUser.getId() + "&userId=" + updatedUser.getId(); // 重定向到员工列表页面
	}
		
}
