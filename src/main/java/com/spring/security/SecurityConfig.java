package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Employee;
import com.spring.security.repository.EmployeeRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/contact").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		// http.addFilterBefore(jwtAuthFilter,
		// UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		Employee employee = employeeRepo.findById(4L).get();
		System.out.println(employee.toString());
		UserDetails admin = User.builder().username("admin").password(encoder().encode("admin")).build();
		UserDetails manju = User.builder().username("manju").password(encoder().encode("manju")).build();
		UserDetails root = User.builder().username(employee.getUsername()).password(encoder().encode(employee.getPassword())).build();
		return new InMemoryUserDetailsManager(admin, manju,root);
	}

	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
