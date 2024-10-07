package com.example.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.loginhandler.LoginSuccessHandler;
import com.example.service.JdbcUserDetailsServices;
import com.example.service.UserService;


@Configuration
public class SecurityConfiguration {
	@Autowired
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserService service;
	@Autowired
	LoginSuccessHandler handler;
	@Autowired
	JdbcUserDetailsServices detailsServices;
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/login", "/register", "/frag1",
				"/css/**" , "/images/**","/javascript/**"
				).permitAll()
				.requestMatchers("/dashboard","/products","/AnotherFile").hasAnyRole("ADMIN","USER")
				.requestMatchers("/addProducts").hasRole("ADMIN")
				.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").successHandler(handler)
						.permitAll());

		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsServices);

	}

}
