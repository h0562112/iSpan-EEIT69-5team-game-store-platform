package com.ispan.eeit69.controller.membercontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Friends;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.memberRepository.FriendRepository;
import com.ispan.eeit69.utils.memberutils.Msg;

@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendRepository friendRepository;

	/** 只能操作自己的好友資料 */
	private void requireSelf(HttpSession session, Integer userId) {
		User me = (User) session.getAttribute("myUser");
		if (me == null || userId == null || me.getId() != userId) {
			throw new AccessDeniedException("只能操作自己的好友資料");
		}
	}

	@PostMapping("/save")
	public Msg addFriend(@RequestBody Friends friend, HttpSession session) {
		requireSelf(session, friend.getUserID1());
		Friends savedFriend = friendRepository.save(friend);

		return Msg.success()
				.setCode(200)
				.setMessage("自定義成功消息")
				.addData("friend", savedFriend);
	}

	@GetMapping("/get/{id}")
	public Msg get(@PathVariable("id") int id, HttpSession session) {
		requireSelf(session, id);
		List<Friends> list = friendRepository.findByUserID1(id);
		return Msg.success().data("friends", list);
	}

	@DeleteMapping("/del/{user1}/{user2}")
	public Msg del(@PathVariable("user1") int user1, @PathVariable("user2") int user2, HttpSession session) {
		requireSelf(session, user1);
		friendRepository.deleteByUserID1AndUserID2(user1, user2);
		return Msg.success();
	}

}
