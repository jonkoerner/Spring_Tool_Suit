package com.jonathan.idea.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonathan.idea.models.User;
import com.jonathan.idea.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserService uS;
	
	public UserController(UserService uS) {
		this.uS = uS;
	}
	
	
	@RequestMapping("/new")
	// This is create an empty user
	public String newUser(@ModelAttribute("user") User user,HttpSession session) {
		uS.logout(session);
		return "newUser.jsp";
	}
	
	@PostMapping("/new")
	// here we are Binding the data to our model 
	public String create(@Valid @ModelAttribute("user") User user, BindingResult res, RedirectAttributes flash,HttpSession session) {
		if(res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/users/new";
		}else {
			User exists = uS.findByEmail(user.getEmail());
			// if there is no one with that email. We now can bind the data using save from the crud repository
			if(exists == null) {
				// this statemant creates the new user object
				User u = uS.create(user);
				// this binds an object to the session
				uS.login(session, u.getId());
				return "redirect:/ideas";
			}else {
				flash.addFlashAttribute("errors","A user with this email already exists.");
				return "redirect:/users/new";
			}
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,RedirectAttributes flash) {
		if(email.length()<1) {
			flash.addFlashAttribute("error","Email cannot be blank. ");
			return "redirect:/users/new";
		}
		
		User user = uS.findByEmail(email);
		
		if(user == null) {
			flash.addFlashAttribute("error","No user with this email was found.");
			return "redirect:/users/new";
		}else {
			if(uS.isMatch(password,user.getPassword())) {
				uS.login(session, user.getId());
				
				return "redirect:/ideas";
			}
			flash.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/users/new";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession s) {
		return uS.redirect();
	}
}