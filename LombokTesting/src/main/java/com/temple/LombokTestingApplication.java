package com.temple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.temple.test.TestLombok;

@SpringBootApplication
public class LombokTestingApplication {

	@Autowired
	TestLombok lombok;
	
	public static void main(String[] args) {
		SpringApplication.run(LombokTestingApplication.class, args);
		
		
		
	}

}
