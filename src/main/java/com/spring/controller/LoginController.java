package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/* 
 * This class provide login logic
 */
@Controller
public class LoginController {

	private static Logger logger = LogManager.getLogger(LoginController.class.getName());

	
	
	/**
	 * Show login page
	 * 
	 * @param model
	 * @param error
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {

		if (error != null) {
			model.addAttribute("error", error);
		}
		return "loginPage";
	}
	
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@ModelAttribute("user") @Valid User user, BindingResult result, HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("Trying to login: {}", user.getUserName());
		String errorMessages = "";
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();

			for (FieldError error : errors) {
				errorMessages += error.getField() + " " + (error.getDefaultMessage()) + ".\t";
			}
			return "redirect:/?error=" + errorMessages;
		}
		try {
		
           if(user.getUserName().equals("Admin") && user.getPassword().equals("123456")){
        	logger.debug("Login  success : {}", user.getUserName());
   			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
   			String [] permissions = {"ROLE_ADMIN" ,"ROLE_DBA"};
   			for (String permission : permissions) {
   				grantedAuths.add(new SimpleGrantedAuthority(permission));
   			}

   			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
   			
   			SecurityContext securityContext = SecurityContextHolder.getContext();
   			securityContext.setAuthentication(authentication);
   			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
           }else{
        		logger.debug("Login  fail : {}", user.getUserName());
        	   throw new Exception("Wrong Username and Password");
           }
		
		}  catch (Exception exception) {
			
			logger.error(exception.getMessage(), exception);
			return "redirect:/?error=" + errorMessages;
			
		} 
		return "redirect:/admin";
	}

}
