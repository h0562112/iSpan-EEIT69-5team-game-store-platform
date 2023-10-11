package com.ispan.eeit69.service;

import java.util.List;

import com.ispan.eeit69.model.Employee;

public interface EmployeeService {
	
	void resetEmployeeTable();
	
	void save(Employee employee);
	
	void deleteById(Integer id);
	
	void update(Employee employee);
	
	Employee findByEmployeeId(String employeeId);
	
	boolean existsByEmployeeId(Employee employee);
	
	Employee findById(Integer id);
	
	List<Employee> findAll();
	
	boolean isPersist(Employee employee);
	
	void detach(Employee employee);
	
	int getTotalEmployeeCount();
	
	List<Employee> getEmployeeSubset(int startIndex, int recordsPerPage);
	
	int getTotalEmployeeCountByKeyword(String keyword);
	
	List<Employee> searchEmployeesByKeyword(String keyword, int page, int recordsPerPage);
}
