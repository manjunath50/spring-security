package com.spring.security.service;

import java.util.List;

import com.spring.security.dto.UserInfo;
import com.spring.security.model.Employee;


public interface DemoService {
	
	List<UserInfo> getUserDetails();

	Employee create(Employee employee);

}
