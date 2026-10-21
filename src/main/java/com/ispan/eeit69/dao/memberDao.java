package com.ispan.eeit69.dao;

import java.sql.SQLException;
import java.util.List;

import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.player_Games_library;

public interface memberDao {

	void resetEmployeeTable();

	void save(Employee employee);

	void update(Employee employee);
	void updateUsertable(User user);

	Employee findBypassWord(String passWord);

	Employee findById(Integer id);
	User findByuserId(Integer id);
	Item findBygameId(Integer id);
	
	List<User> findUserAll();
	List<Avatar> findAvatarAll();
	List<Employee> findAll();


	Employee findByname(String name);

	void deleteById(Integer id);

	List<player_Games_library> findplayergaAll();

	List<Object[]> findByfriendsid(Integer id);
	List<Object[]> findByfriendsidNew(Integer id);
	List<Object[]> findByPlayerGameId(Integer id);

	List<FriendsMessages> findByfriendmessages(Integer senderUserid, Integer receiverUserid);

	void updateUsertable(User user, String base64Image) throws SQLException;

	List<Object[]> findByuserAllavatar();







	

}