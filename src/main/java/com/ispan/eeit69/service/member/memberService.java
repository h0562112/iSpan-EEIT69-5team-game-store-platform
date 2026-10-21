package com.ispan.eeit69.service.member;

import java.util.List;

import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.player_Games_library;

public interface memberService {
	void resetEmployeeTable();
	void save(Employee employee);
	void update(Employee employee);
	void Usertableupdate(User user);
	
	Employee findBypassWord(String passWord);
	
	boolean existsBypassWord(String passWord);
	
	Employee findById(Integer id);
	User findByUserId(Integer id);
	Avatar findAvatarByUserId(Integer userId);

	List<Employee> findAll();
	List<User> findUserAll();
	
	void deleteById(Integer id);
	Employee findByname(String name);
	List<player_Games_library> findplayergaAll();
	List<Object[]> findByfriendsid(Integer id);
	List<Object[]> findByfriendsidNew(Integer id);
	List<Object[]> findByPlayerGameId(Integer id);
	Item findBygameId(Integer id);
	List<FriendsMessages> findByfriendmessages();
	List<FriendsMessages> findByfriendmessages(Integer senderUserid, Integer receiverUserid);
	List<Object[]> findByuserAllavatar();
	List<Avatar> findAvatarAll();
	

}
