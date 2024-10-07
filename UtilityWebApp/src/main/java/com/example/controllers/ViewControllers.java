package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

@Controller
public class ViewControllers {
	@Autowired
    private UserService service;
	
	@GetMapping("/")
	public String base(Model model) {
		model.addAttribute("title","Login or SignUp");
		return "public/index";
	}
	
	@GetMapping("/login")
	public String login(Model model , User user) {
		model.addAttribute("title","Login or SignUp");
		return "public/index";
	}
	
	@GetMapping("/home")
	public String home() {
		return "personal/products";
	}
	
	@GetMapping("/frag1")
	public String frag1() {
		return "public/frag1";
	}
	
	@GetMapping("/products")
	public String products() {
		return "personal/products";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "personal/dashboard";
	}
	
	@GetMapping("/AnotherFile")
	public String anotherFile(){
		return "personal/AnotherFile";
	}
	
	@GetMapping("/addProducts")
	public String addNewProduct() {
		return "admin/addProduct";
	}
	
	
	@PostMapping("/register")
	public String register(@Valid User user
			, BindingResult bindingResult  , Model model) {
		
		if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", user);
        }
		
		try {
				service.save(user);
		}catch (Exception e) {
			bindingResult.rejectValue("username", "user.username","The UserName is Already Exixsts");
			e.printStackTrace();
		}
		return "public/index";
	}

}
