package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage(Model model) {
		System.out.println("HelloController.adminPage()");

		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page - Admin Page!");

		return "adminPage";

	}

	@RequestMapping(value = "/dba**", method = RequestMethod.GET)
	public String dbaPage(Model model) {

		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page - Database Page!");

		return "adminPage";

	}

}