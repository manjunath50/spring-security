package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.dto.UserInfo;
import com.spring.security.model.Employee;
import com.spring.security.service.DemoService;

import reactor.core.publisher.Mono;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@GetMapping(path = "/getDetails")
	public ResponseEntity<List<UserInfo>> getDetails() {

		return ResponseEntity.ok(demoService.getUserDetails());
	}

	@GetMapping(path = "/contact")
	public Mono<String> contact() {
		return Mono.justOrEmpty("ph:456789999");
	}

	@GetMapping("/greeting")
	public Mono<String> greeting(Authentication authentication) {
		
		String username = authentication.getName();
		String password = ((UserDetails) authentication.getPrincipal()).getPassword();
		System.out.println("username : "+username+" password : "+password);
		Employee employee = new Employee(username, password);
		demoService.create(employee);
		return Mono.justOrEmpty("employee created : " + username);

	}
	
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return demoService.create(employee);
	}

}
