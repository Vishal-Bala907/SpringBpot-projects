package com.example.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserDetailsServices implements UserDetailsService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	.usersByUsernameQuery("SELECT username, password , enabled FROM user WHERE username=?")
//	.authoritiesByUsernameQuery("SELECT username , role FROM user WHERE username=?")

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String query = "SELECT username, password , enabled FROM user WHERE username=?";
		return jdbcTemplate.queryForObject(query , new String[] {username} , (rs, rowNum)->{
			String dbUsername = rs.getString("username");
            String password = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");
            return new org.springframework.security.core.userdetails.User(
                    dbUsername, password, enabled, true, true, true, Collections.emptyList());
		});
	}

}
