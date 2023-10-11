package com.ispan.eeit69.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.dao.memberDao;
import com.ispan.eeit69.model.Avatar;
import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.FriendsMessages;
import com.ispan.eeit69.model.Item;
import com.ispan.eeit69.model.User;
import com.ispan.eeit69.model.player_Games_library;
@Repository
public class memberDaoImpl implements memberDao {

//	SessionFactory factory;
	@PersistenceContext
	EntityManager  entityManager;   // session
	
//	@Autowired
//	public EmployeeDaoImpl(SessionFactory factory) {
//		this.factory = factory;
//	}

	@Override
	public void resetEmployeeTable() {
		throw new RuntimeException("本系統未提供此功能");
	}

	@Override
	public void save(Employee employee) {
//		Session session = factory.getCurrentSession();
//		session.save(employee);
		entityManager.persist(employee);
	}

	@Override
	public void update(Employee employee) {
	    Employee tmp = findById(employee.getId());
	    if (tmp != null) {
	        // 更新需要修改的字段
	        tmp.setName(employee.getName());
	        tmp.setAccount(employee.getAccount());
	        tmp.setEmail(employee.getEmail());
	        tmp.setPicture(employee.getPicture());
	        tmp.setPassword(employee.getPassword());

	        // ... 其他需要更新的字段

	        // 實際更新數據庫
	        entityManager.merge(tmp);
	    }
	}
	
	@Override
	public void updateUsertable(User user) {
		User tmp = findByuserId(user.getId());
	    if (tmp != null) {
	        // 更新需要修改的字段
	        tmp.setUsername(user.getUsername());
	        tmp.setEmail(user.getEmail());
	        tmp.setPassword(user.getPassword());

	        // ... 其他需要更新的字段

	        // 實際更新數據庫
	        entityManager.merge(tmp);
	    }
	}
	
	@Override
	public Employee findBypassWord(String passWord) {

		
		Employee result = null;
		String hql = "FROM Employee WHERE passWord = :eid";
		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
			                           			 .setParameter("eid", passWord)
		                           			 	 .getResultList();
		if (employees.size() > 0) {
			result = employees.get(0);
		}
		return result;
	}
	
	public Item findBygameId(Integer id) {
//		Session session = factory.getCurrentSession();
//		Employee result = session.get(Employee.class, id);
//		return result;
		
//		Session session = factory.getCurrentSession();
		Item result = entityManager.find(Item.class, id);
		return result;
	}
	
	@Override
	public Employee findById(Integer id) {
//		Session session = factory.getCurrentSession();
//		Employee result = session.get(Employee.class, id);
//		return result;
		
//		Session session = factory.getCurrentSession();
		Employee result = entityManager.find(Employee.class, id);
		return result;
	}
	@Override
	public User findByuserId(Integer id) {
		
		User result = entityManager.find(User.class, id);
		return result;
	}

	@Override
	public List<Employee> findAll() {

//		String hql = "FROM Employee";
//		
//		Session session = factory.getCurrentSession(); 
//		List<Employee>  employees = session.createQuery(hql, Employee.class)
//				                           .getResultList();
//		return employees;
		
		String hql = "FROM Employee";
		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
				                                 .getResultList();
		return employees;
	}
	
	@Override
	public List<User> findUserAll() {
		
		String hql = "FROM User";
		List<User>  User = entityManager.createQuery(hql, User.class)
				                                 .getResultList();
		return User;
	}
	
	@Override
	public List<Avatar> findAvatarAll() {
		
		String hql = "FROM Avatar";
		List<Avatar>  Avatar = entityManager.createQuery(hql, Avatar.class)
				                                 .getResultList();
		return Avatar;
	}
	
	@Override
	public Employee findByname(String name) {
	    Employee result = null;
	    String hql = "FROM Employee WHERE name = :name"; // 将参数名改为 :name
	    List<Employee> employees = entityManager.createQuery(hql, Employee.class)
	            .setParameter("name", name) // 使用 :name 作为参数名
	            .getResultList();
	    if (employees.size() > 0) {
	        result = employees.get(0);
	    }
	    return result;
	}


	@Override
	public void deleteById(Integer id) {
//		String hql = "DELETE FROM Employee e WHERE e.id = :id";
//		Session session = factory.getCurrentSession();
//		session.createQuery(hql)
//		       .setParameter("id", id)
//		       .executeUpdate();
		String hql = "DELETE FROM Employee e WHERE e.id = :id";
		entityManager.createQuery(hql)
		             .setParameter("id", id)
		             .executeUpdate();
	}

	@Override
	public List<player_Games_library> findplayergaAll() {

		
		String hql = "FROM player_Games_library";
		List<player_Games_library>  test = entityManager.createQuery(hql, player_Games_library.class)
				                                 .getResultList();
		return test;
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object[]> findByfriendsid(Integer id) {
//	    String sql = "SELECT U.name AS UserName, F.name AS userID2, F.email , F.picture " +
//	                 "FROM friends " + // 注意这里使用的是正确的实体类名称
//	                 "JOIN member_Test AS U ON friends.userID1 = U.id OR friends.userID2 = U.id " +
//	                 "JOIN member_Test AS F ON (friends.userID1 = F.id AND friends.userID2 = U.id) OR (friends.userID2 = F.id AND friends.userID1 = U.id) " +
//	                 "WHERE (friends.userID1 = :eid OR friends.userID2 = :eid) AND friends.status = 'accept' AND F.id != :eid";
//
//	    return entityManager.createNativeQuery(sql)
//	                        .setParameter("eid", id)
//	                        .getResultList();
//	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByfriendsidNew(Integer id) {
	    String sql = "SELECT userID2 AS US2, userID1, U.username AS UserName, F.username AS friendname, F.email" +
	            " FROM friends " + // 注意這裡添加了一個空格，以確保 SQL 語法正確
	            " JOIN user AS U ON friends.userID1 = U.id " +
	            " JOIN user AS F ON friends.userID2 = F.id " +
	            " WHERE friends.userID1 = :eid AND friends.status = 'accept' AND F.id != :eid";

	    return entityManager.createNativeQuery(sql)
	            .setParameter("eid", id)
	            .getResultList();
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByfriendsid(Integer id) {
		String sql = "SELECT userID2 AS US2,userID1, U.username AS UserName, F.username AS friendname, F.email, I.picture " +
				"FROM friends " + // 注意这里使用的是正确的实体类名称
				"JOIN user AS U ON friends.userID1 = U.id " +
				"JOIN user AS F ON friends.userID2 = F.id " +
				"JOIN avatar AS I ON user_id = F.id " +
				"WHERE friends.userID1 = :eid AND friends.status = 'accept' AND F.id != :eid";
		
		return entityManager.createNativeQuery(sql)
				.setParameter("eid", id)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByPlayerGameId(Integer id) {
	    String sql = "SELECT M.gameID, M.playerID, F.username AS playerName, G.itemName, G.itemPicture " +
	                 "FROM player_Games_library M " +
	                 "JOIN user F ON M.playerID = F.id " +
	                 "JOIN items G ON M.gameID = G.id " +
	                 "WHERE M.playerID = :eid";

	    return entityManager.createNativeQuery(sql)
	                        .setParameter("eid", id)
	                        .getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByuserAllavatar() {
	    String sql = "SELECT user.id, user.username, user.email, avatar.picture " +
	                 "FROM user " +
	                 "LEFT JOIN avatar ON user.id = avatar.user_id";

	    return entityManager.createNativeQuery(sql)
	                        .getResultList();
	}

	
	@Override
	public List<FriendsMessages> findByfriendmessages(Integer senderUserid ,Integer receiverUserid) {
	    String hql = "FROM FriendsMessages WHERE (senderUserid = :sender AND receiverUserid = :receive) OR (senderUserid = :receive AND receiverUserid = :sender)"; // 注意这里的括号，以及类名和字段名应与实体类相匹配
	    List<FriendsMessages> friendsMessages = entityManager.createQuery(hql, FriendsMessages.class)
	            .setParameter("sender", senderUserid)
	            .setParameter("receive", receiverUserid)
	            .getResultList();
	    return friendsMessages; // 返回查询结果列表
	}

	@Override
	public void updateUsertable(User user, String base64Image) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
