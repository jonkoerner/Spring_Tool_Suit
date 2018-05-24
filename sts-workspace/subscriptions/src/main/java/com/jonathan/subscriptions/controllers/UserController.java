package com.jonathan.subscriptions.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonathan.subscriptions.models.User;
import com.jonathan.subscriptions.services.UserService;

@Controller
@RequestMapping("")
public class UserController {
	private UserService uS;
	public UserController(UserService uS) {
		this.uS = uS;
	}
	@RequestMapping(value= {"/users/new","", "/", "/welcome"})
	public String newUser(@ModelAttribute("user") User user,HttpSession session) {
		uS.logout(session);
		return "newUser.jsp";
	}
	
	@PostMapping("/users/new")
	public String create(@Valid @ModelAttribute("user") User user, BindingResult res, RedirectAttributes flash,HttpSession session) {
		if(res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/users/new";
		}else {
			User exists = uS.findByEmail(user.getEmail());
			if(exists == null) {
				User u = uS.create(user);
				uS.login(session, u.getId());
				if(u.getId() == 1) {
					return "redirect:/subscriptions/admin";
				}
				return "redirect:/subscriptions";
			}else {
				flash.addFlashAttribute("errors","A user with this email already exists.");
				return "redirect:/users/new";
			}
		}
	}
	
	@PostMapping("/users/login")
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
	@RequestMapping("/users/logout")
	public String logout(HttpSession s) {
		return uS.redirect();
	}
}
