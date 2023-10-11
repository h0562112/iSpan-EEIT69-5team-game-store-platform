//package com.ispan.eeit69.dao.impl;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Repository;
//
//import com.ispan.eeit69.dao.EmployeeDao;
//import com.ispan.eeit69.model.Employee;
//@Repository
//public class EmployeeDaoImpl implements EmployeeDao {
//
////	SessionFactory factory;
//	@PersistenceContext
//	EntityManager entityManager;  
//	
//	@Override
//	public void resetEmployeeTable() {
//		throw new RuntimeException("本系統未提供此功能");
//	}
//
//	@Override
//	public void save(Employee employee) {
////		Session session = factory.getCurrentSession();
////		session.save(employee);
//		entityManager.persist(employee);
//	}
//
//	@Override
//	public void update(Employee employee) {
//
////		Session session = factory.getCurrentSession(); 
////		Employee tmp = findById(employee.getId());
////		employee.setCreated_at(tmp.getCreated_at());
////		session.evict(tmp);
////		session.saveOrUpdate(employee);   // 有可能要改為 merge();
////		session.merge(employee);   
//		entityManager.merge(employee);
//	}
//
//	@Override
//	public Employee findByEmployeeId(String employeeId) {
////		Employee result = null;
////		String hql = "FROM Employee WHERE employeeId = :eid";
////		Session session = factory.getCurrentSession(); 
////		List<Employee>  employees = session.createQuery(hql, Employee.class)
////				                           .setParameter("eid", employeeId)
////				                           .getResultList();
////		if (employees.size() > 0) {
////			result = employees.get(0);
////		}
////		return result;
//		
//		Employee result = null;
//		String hql = "FROM Employee WHERE employeeId = :eid";
//		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
//				                           .setParameter("eid", employeeId)
//				                           .getResultList();
//		if (employees.size() > 0) {
//			result = employees.get(0);
//		}
//		return result;
//		
//		
//	}
//	
//	@Override
//	public Employee findById(Integer id) {
////		Session session = factory.getCurrentSession();
////		Employee result = session.get(Employee.class, id);
////		return result;
//		Employee result = entityManager.find(Employee.class, id);
//		return result;
//	}
//
//	@Override
//	public List<Employee> findAll() {
//
////		String hql = "FROM Employee";
////		
////		Session session = factory.getCurrentSession(); 
////		List<Employee>  employees = session.createQuery(hql, Employee.class)
////				                           .getResultList();
////		return employees;
//		
//		String hql = "FROM Employee";
//		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
//				                                 .getResultList();
//		return employees;		
//		
//		
//	}
//
//	@Override
//	public void deleteById(Integer id) {
////		String hql = "DELETE FROM Employee e WHERE e.id = :id";
////		Session session = factory.getCurrentSession();
////		session.createQuery(hql)
////		       .setParameter("id", id)
////		       .executeUpdate();
//		String hql = "DELETE FROM Employee e WHERE e.id = :id";
//		
//		entityManager.createQuery(hql)
//		       .setParameter("id", id)
//		       .executeUpdate();
//	}
//}
