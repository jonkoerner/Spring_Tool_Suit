package com.jonathan.subscriptions.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonathan.subscriptions.models.Subscription;
import com.jonathan.subscriptions.models.User;
import com.jonathan.subscriptions.services.SubscriptionService;
import com.jonathan.subscriptions.services.UserService;


@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {
	private SubscriptionService sS;
	private UserService uS;

	public SubscriptionController(SubscriptionService sS,UserService uS) {
		this.sS = sS;
		this.uS = uS;
	}
	@RequestMapping("")
	public String allSubscriptions(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			model.addAttribute("user",u);
			model.addAttribute("subscription",sS.all());
			if(u.getId() != 1) {
				return "customer.jsp";
			}
			else {
				return "admin.jsp";
			}	
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/new")
	public String newSubscription(Model model, HttpSession session, @ModelAttribute("subscription") Subscription subscription) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}	
		return "newSubscription.jsp";
	}
	
	@PostMapping("/new")
	public String createSubscription(@Valid @ModelAttribute("subscription") Subscription subscription, BindingResult res,RedirectAttributes flash, HttpSession session) {
		if (res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/courses/new";
		}
//		if(session.getAttribute("id") != null) {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//			
//			subscription.setCreator(u);
//			iS.create(subscription);
//			return "redirect:/subscriptions";
//		}
		
		return "redirect:/new";
		}
}
