package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	//translating sqlexception to DataAccessException
}
