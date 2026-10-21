package com.ispan.eeit69.controller.membercontroller;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.service.member.AvatarService;
import com.ispan.eeit69.service.member.memberService;

@Controller
public class FindEmployeeController {

	AvatarService avatarService;
	memberService employeeService;
	BCryptPasswordEncoder bcryptPasswordEncoder;

	public FindEmployeeController(memberService employeeService, AvatarService avatarService,
			BCryptPasswordEncoder bcryptPasswordEncoder) {
		this.employeeService = employeeService;
		this.avatarService = avatarService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}

	/** 目前登入者 id，未登入回傳 null */
	private Integer currentUserId(HttpSession session) {
		User me = (User) session.getAttribute("myUser");
		return me == null ? null : me.getId();
	}

	/** 會員資料只允許本人存取 */
	private void requireSelf(HttpSession session, Integer userId) {
		Integer me = currentUserId(session);
		if (me == null || !me.equals(userId)) {
			throw new AccessDeniedException("只能存取自己的會員資料");
		}
	}

	/** 取得使用者頭像 (Base64 字串)，沒有頭像回傳 null */
	private String avatarBase64(Integer userId) {
		Avatar image = employeeService.findAvatarByUserId(userId);
		if (image == null || image.getPicture() == null) {
			return null;
		}
		return clobToString(image.getPicture());
	}

	private String clobToString(Clob clob) {
		try {
			return clob.getSubString(1, (int) clob.length());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void addIfPresent(Model model, String name, String value) {
		if (value != null) {
			model.addAttribute(name, value);
		}
	}

	@GetMapping("/findEmployee.do")
	public String findById(Model model, @RequestParam Integer id, HttpSession session) {
		requireSelf(session, id);
		User user = employeeService.findByUserId(id);
		addIfPresent(model, "itemPictureBase64", avatarBase64(id));
		model.addAttribute("user", user);
		return "member-profile/member-profile";
	}

	@GetMapping("/updatep_Profile")
	public String updatep_Profile(Model model, @RequestParam Integer id, HttpSession session) {
		requireSelf(session, id);
		User user = employeeService.findByUserId(id);
		addIfPresent(model, "itemPictureBase64", avatarBase64(id));
		model.addAttribute(user);
		return "member-profile/member_Update";
	}

	@GetMapping("/findgameid")
	public String findgameid(Model model, @RequestParam Integer id) {
		Item item = employeeService.findBygameId(id);

		if (item != null && item.getItempicture() != null) {
			addIfPresent(model, "itemPictureBase64", clobToString(item.getItempicture()));
		}

		model.addAttribute("item", item);
		return "member-profile/game-page";
	}

	@GetMapping("/newFriends")
	public String findByfriendss(Model model, @RequestParam Integer id, HttpSession session) {
		requireSelf(session, id);
		List<Object[]> user = employeeService.findByuserAllavatar();
		List<Object[]> friendsList = employeeService.findByfriendsidNew(id);
		User employee = employeeService.findByUserId(id);

		addIfPresent(model, "myimage", avatarBase64(id));
		model.addAttribute("user", user);
		model.addAttribute("employee", employee);
		model.addAttribute("friendsList", friendsList);
		return "member-profile/new_friend-list";
	}

	@GetMapping("/findfriendsid")
	public String findByfriendsid(Model model, @RequestParam Integer id, HttpSession session) {
		requireSelf(session, id);
		List<Object[]> friendsList = employeeService.findByfriendsid(id);
		List<Object[]> friendsListnew = employeeService.findByfriendsidNew(id);
		User employee = employeeService.findByUserId(id);

		addIfPresent(model, "itemPictureBase64", avatarBase64(id));
		model.addAttribute("employee", employee);
		model.addAttribute("friendsListnew", friendsListnew);
		model.addAttribute("friendsList", friendsList);
		return "member-profile/friend-list";
	}

	@GetMapping("/findPlayergameid")
	public String findPlayergameid(Model model, @RequestParam Integer id, HttpSession session) {
		requireSelf(session, id);
		List<Object[]> PlayergameList = employeeService.findByPlayerGameId(id);
		model.addAttribute("PlayergameList", PlayergameList);
		return "member-profile/game-library";
	}

	@GetMapping("/findByfriendmessage")
	public String findByfriendmessage(Model model, @RequestParam Integer senderUserid,
			@RequestParam Integer receiverUserid, HttpSession session) {
		// 只有對話的雙方可以看訊息
		Integer me = currentUserId(session);
		if (me == null || (!me.equals(senderUserid) && !me.equals(receiverUserid))) {
			throw new AccessDeniedException("只能查看自己的聊天紀錄");
		}
		List<FriendsMessages> message = employeeService.findByfriendmessages(senderUserid, receiverUserid);

		addIfPresent(model, "receiverUserimage", avatarBase64(senderUserid));
		addIfPresent(model, "senderUserimage", avatarBase64(receiverUserid));

		model.addAttribute("message", message);
		model.addAttribute("senderUserid", senderUserid);
		model.addAttribute("receiverUserid", receiverUserid);
		return "member-profile/messagePage";
	}

	@PostMapping("/updateEmployee.do")
	public String updateEmployee(@ModelAttribute User updatedUser, @RequestParam("file") MultipartFile file,
			HttpSession session) throws SQLException, IOException {
		// 只能修改自己的資料，不採用表單送來的 id
		Integer myId = currentUserId(session);
		if (myId == null) {
			throw new AccessDeniedException("請先登入");
		}

		User existingUser = employeeService.findByUserId(myId);

		if (existingUser != null) {
			String newPassword = updatedUser.getPassword();
			if (newPassword != null && !newPassword.trim().isEmpty()) {
				existingUser.setPassword(bcryptPasswordEncoder.encode(newPassword));
			}
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(updatedUser.getEmail());
			employeeService.Usertableupdate(existingUser);

			if (!file.isEmpty()) {
				String base64Encoded = Base64.getEncoder().encodeToString(file.getBytes());
				Clob imageClob = new SerialClob(base64Encoded.toCharArray());

				Avatar avatar = avatarService.findAvatarByUserId(myId);
				if (avatar == null) {
					avatar = new Avatar();
					avatar.setUser(existingUser);
				}
				avatar.setPicture(imageClob);
				avatarService.saveOrUpdateAvatar(avatar);

				// 同步更新導覽列顯示的頭像 (登入時才寫入 session，這裡要一起更新)
				session.setAttribute("itemPictureBase64", base64Encoded);
			}
			session.setAttribute("myUser", existingUser);
		}
		return "redirect:/findEmployee.do?id=" + myId + "&userId=" + myId;
	}

}
