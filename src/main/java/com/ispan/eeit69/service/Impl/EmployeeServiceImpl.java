package com.ispan.eeit69.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.dao.EmployeeDao;
import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

//	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
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



	@Override
	public Employee findById(Integer id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		employeeDao.deleteById(id);
	}
	
	@Override
	public Employee findByEmployeeId(String employeeId) {
		Employee emp = employeeDao.findByEmployeeId(employeeId);
		return emp;
	}
	
	@Override
	public boolean existsByEmployeeId(Employee employee) {
		if (employeeDao.isPersist(employee)) {
			employeeDao.detach(employee);
		}
		Employee emp = findByEmployeeId(employee.getEmployeeId());
		return emp != null;
	}

	@Override
	public boolean isPersist(Employee employee) {
		boolean ans = employeeDao.isPersist(employee);
		return ans;
	}

	@Override
	public void detach(Employee employee) {
		employeeDao.detach(employee);
	}
	
	@Override
    public int getTotalEmployeeCount() {
        return employeeDao.getTotalEmployeeCount(); // 在 EmployeeDAO 中实现该方法
    }
	
    @Override
    public List<Employee> getEmployeeSubset(int startIndex, int recordsPerPage) {
        return employeeDao.getEmployeeSubset(startIndex, recordsPerPage);
    }
    
    @Override
    public int getTotalEmployeeCountByKeyword(String keyword) {
        return employeeDao.countEmployeesByKeyword(keyword);
    }
    
    @Override
    public List<Employee> searchEmployeesByKeyword(String keyword, int page, int recordsPerPage) {
        int startIndex = (page - 1) * recordsPerPage;
        return employeeDao.findEmployeesByKeyword(keyword, startIndex, recordsPerPage);
    }

}
