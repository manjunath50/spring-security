package com.spring.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.SecurityConfig;
import com.spring.security.dto.UserInfo;
import com.spring.security.model.Employee;
import com.spring.security.repository.EmployeeRepo;
import com.spring.security.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	SecurityConfig securityConfig;

	@Override
	public List<UserInfo> getUserDetails() {
		// TODO Auto-generated method stub

		List<UserInfo> users = new ArrayList<UserInfo>();
		users.add(new UserInfo("admin", "werty"));
		users.add(new UserInfo("user", "weyyrty"));
		return users;
	}

	@Override
	public Employee create(Employee employee) {
		employee.setPassword(securityConfig.encoder().encode(employee.getPassword()));
		return employeeRepo.save(employee);
	}

}
