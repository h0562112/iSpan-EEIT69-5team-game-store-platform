package com.ispan.eeit69.service.Impl.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.dao.memberDao;
import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.player_Games_library;
import com.ispan.eeit69.repository.memberRepository.AvatarRepository;
import com.ispan.eeit69.service.member.memberService;

@Service
@Transactional
public class memberServiceImpl implements memberService {
	
	@Autowired
	memberDao employeeDao;
	private AvatarRepository friendRepository;
	
//	@Autowired
	public memberServiceImpl(memberDao employeeDao, AvatarRepository friendRepository) {
		this.friendRepository = friendRepository;
		this.employeeDao = employeeDao;
	}

	@Override
	public void resetEmployeeTable() {
		employeeDao.resetEmployeeTable();
	}

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	public void Usertableupdate(User user) {
		employeeDao.updateUsertable(user);
	}

	@Override
	public Employee findBypassWord(String passWord) {
		return employeeDao.findBypassWord(passWord);
	}

	@Override
	public Employee findById(Integer id) {
		return employeeDao.findById(id);
	}

	public User findByUserId(Integer id) {
		return employeeDao.findByuserId(id);
	}
	
	@Override
	public Item findBygameId(Integer id) {
		return employeeDao.findBygameId(id);
	}
	
	
	@Override
	public Avatar findAvatarByUserId(Integer userId) {
	    return friendRepository.findByUserId(userId);
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
	
	@Override
	public List<User> findUserAll() {
		return employeeDao.findUserAll();
	}
	
	@Override
	public List<Avatar> findAvatarAll() {
		return employeeDao.findAvatarAll();
	}

	@Override
	public void deleteById(Integer id) {
		 employeeDao.deleteById(id);
	}

	@Override
	public boolean existsBypassWord(String passWord) {
		return findBypassWord(passWord) != null ? true : false;
	}

	@Override
	public Employee findByname(String name) {
		return employeeDao.findByname(name);
	}

	@Override
	public List<player_Games_library> findplayergaAll() {
		return employeeDao.findplayergaAll();
	}

	@Override
	public List<Object[]> findByfriendsid(Integer id) {
		 return employeeDao.findByfriendsid(id);
	}
	
	@Override
	public List<Object[]> findByfriendsidNew(Integer id) {
		 return employeeDao.findByfriendsidNew(id);
	}
	
	@Override
	public List<Object[]> findByPlayerGameId(Integer id) {
		 return employeeDao.findByPlayerGameId(id);
	}
	
	@Override
	public List<Object[]> findByuserAllavatar() {
		return employeeDao.findByuserAllavatar();
	}
	
	@Override
	public List<FriendsMessages> findByfriendmessages (Integer senderUserid,Integer receiverUserid) {
		return employeeDao.findByfriendmessages(senderUserid,receiverUserid);
	}

	@Override
	public List<FriendsMessages> findByfriendmessages() {
		// TODO Auto-generated method stub
		return null;
	}
}
