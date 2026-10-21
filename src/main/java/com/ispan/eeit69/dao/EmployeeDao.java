package com.ispan.eeit69.dao;

import java.util.List;

import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.model.Item;

public interface EmployeeDao {
	
	void resetEmployeeTable();
	
	void save(Employee employee);
	
	void deleteById(Integer id);

	void update(Employee employee);
	
	Employee findByEmployeeId(String employeeId);
	
	Employee findById(Integer id);
	
	List<Employee> findAll();
	
	boolean isPersist(Employee employee);

	void detach(Employee employee);
	
	int getTotalEmployeeCount();
	
	List<Employee> getEmployeeSubset(int startIndex, int recordsPerPage);
	
    int countEmployeesByKeyword(String keyword);
    
    public List<Employee> findEmployeesByKeyword(String keyword, int startIndex, int recordsPerPage);
}
