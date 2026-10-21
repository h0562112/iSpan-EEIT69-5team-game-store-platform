package com.ispan.eeit69.controller.membercontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Friends;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.repository.UserRepository;
import com.ispan.eeit69.repository.memberRepository.FriendRepository;
import com.ispan.eeit69.utils.memberutils.MyWebSocket;
import com.ispan.eeit69.utils.memberutils.Msg;

@RestController
@RequestMapping("/friend")
public class FriendController {

	private static final String PENDING = "pending";
	private static final String ACCEPT = "accept";

	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private UserRepository userRepository;

	/** 只能操作自己的好友資料 */
	private void requireSelf(HttpSession session, Integer userId) {
		User me = (User) session.getAttribute("myUser");
		if (me == null || userId == null || me.getId() != userId) {
			throw new AccessDeniedException("只能操作自己的好友資料");
		}
	}

	/**
	 * 送出好友邀請。邀請寫進資料庫，對方不在線上也不會遺失；
	 * 對方上線（或重新整理頁面）後就能看到並同意。
	 */
	@PostMapping("/request/{from}/{to}")
	@Transactional
	public Msg request(@PathVariable("from") int from, @PathVariable("to") int to, HttpSession session) {
		requireSelf(session, from);
		if (from == to || !userRepository.existsById(to)) {
			return Msg.fail().setMessage("找不到這位使用者");
		}

		Friends mine = friendRepository.findFirstByUserID1AndUserID2(from, to);
		if (mine != null) {
			return Msg.success().setMessage(ACCEPT.equals(mine.getStatus()) ? "你們已經是好友" : "已送出邀請，等待對方同意");
		}

		// 對方早就邀請過我 → 直接成為好友
		Friends theirs = friendRepository.findFirstByUserID1AndUserID2(to, from);
		if (theirs != null) {
			if (!ACCEPT.equals(theirs.getStatus())) {
				makeFriends(theirs, from, to);
			}
			return Msg.success().setMessage("你們已成為好友");
		}

		friendRepository.save(new Friends(null, from, to, PENDING));
		// 對方剛好在線上就即時提醒；不在線也沒關係，之後上線會從資料庫讀到
		MyWebSocket.notifyUser(String.valueOf(to), "friend_request", String.valueOf(from));
		return Msg.success().setMessage("已送出邀請，等待對方同意");
	}

	/** 同意 requester 寄給我的邀請 */
	@PostMapping("/accept/{me}/{requester}")
	@Transactional
	public Msg accept(@PathVariable("me") int me, @PathVariable("requester") int requester, HttpSession session) {
		requireSelf(session, me);
		Friends invite = friendRepository.findFirstByUserID1AndUserID2(requester, me);
		if (invite == null) {
			return Msg.fail().setMessage("沒有這筆邀請");
		}
		makeFriends(invite, me, requester);
		MyWebSocket.notifyUser(String.valueOf(requester), "friend_accepted", String.valueOf(me));
		return Msg.success();
	}

	/** 拒絕（刪除）requester 寄給我的邀請 */
	@PostMapping("/reject/{me}/{requester}")
	@Transactional
	public Msg reject(@PathVariable("me") int me, @PathVariable("requester") int requester, HttpSession session) {
		requireSelf(session, me);
		Friends invite = friendRepository.findFirstByUserID1AndUserID2(requester, me);
		if (invite != null && PENDING.equals(invite.getStatus())) {
			friendRepository.delete(invite);
		}
		return Msg.success();
	}

	/** 寄給我、尚未處理的邀請（含寄件者名稱） */
	@GetMapping("/pending/{id}")
	public Msg pending(@PathVariable("id") int id, HttpSession session) {
		requireSelf(session, id);
		List<Map<String, Object>> result = new ArrayList<>();
		for (Friends f : friendRepository.findByUserID2AndStatus(id, PENDING)) {
			User sender = userRepository.findById(f.getUserID1()).orElse(null);
			if (sender == null) {
				continue;
			}
			Map<String, Object> row = new HashMap<>();
			row.put("userId", sender.getId());
			row.put("username", sender.getUsername());
			result.add(row);
		}
		return Msg.success().data("pending", result);
	}

	/** 把邀請改成 accept，並補上反向那一筆（好友清單只查 userID1 = 自己） */
	private void makeFriends(Friends invite, int me, int requester) {
		invite.setStatus(ACCEPT);
		friendRepository.save(invite);
		if (friendRepository.findFirstByUserID1AndUserID2(me, requester) == null) {
			friendRepository.save(new Friends(null, me, requester, ACCEPT));
		} else {
			Friends mine = friendRepository.findFirstByUserID1AndUserID2(me, requester);
			mine.setStatus(ACCEPT);
			friendRepository.save(mine);
		}
	}

	@GetMapping("/get/{id}")
	public Msg get(@PathVariable("id") int id, HttpSession session) {
		requireSelf(session, id);
		List<Friends> list = friendRepository.findByUserID1(id);
		return Msg.success().data("friends", list);
	}

	/** 刪好友：兩個方向都刪，避免單邊殘留 */
	@DeleteMapping("/del/{user1}/{user2}")
	public Msg del(@PathVariable("user1") int user1, @PathVariable("user2") int user2, HttpSession session) {
		requireSelf(session, user1);
		friendRepository.deleteByUserID1AndUserID2(user1, user2);
		friendRepository.deleteByUserID1AndUserID2(user2, user1);
		return Msg.success();
	}

}
