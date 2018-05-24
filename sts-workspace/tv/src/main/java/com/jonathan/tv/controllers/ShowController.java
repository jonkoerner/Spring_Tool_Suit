package com.jonathan.tv.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonathan.tv.models.Show;
import com.jonathan.tv.models.Rating;
import com.jonathan.tv.models.User;
import com.jonathan.tv.services.RatingService;
import com.jonathan.tv.services.ShowService;
import com.jonathan.tv.services.UserService;

@Controller
@RequestMapping("/shows")
public class ShowController {
	private ShowService sS;
	private UserService uS;
	private RatingService rS;
	public ShowController(ShowService sS,UserService uS,RatingService rS) {
		this.sS = sS;
		this.uS = uS;
		this.rS = rS;
	}
	
	@RequestMapping("")
	public String allShows(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			
			model.addAttribute("user",u);
			model.addAttribute("ideas",sS.all());
			
			return "ideas.jsp";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/new")
	public String newIdea(Model model, HttpSession session, @ModelAttribute("show") Show show) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}	
		return "newShow.jsp";
	}
	
//	@PostMapping("/new")
//	public String createShow(@Valid @ModelAttribute("Show") Show show, BindingResult res,RedirectAttributes flash, HttpSession session) {
//		if (res.hasErrors()) {
//			flash.addFlashAttribute("errors", res.getAllErrors());
//			return "redirect:/courses/new";
//		}
//		if(session.getAttribute("id") != null) {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//			
//			show.setCreator(u);
//			sS.create(show);
//			return "redirect:/show";
//		}
//		
//		return "redirect:/new";
//	}
//	
//	@RequestMapping("/rating/{id}")
//	public String likeIdea(@PathVariable("id")Long id, HttpSession session) {
//		if(session.getAttribute("id") != null) {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//		
//			Optional<Show> idea = sS.find(id);
//			Show i = idea.get();
//			
//			Rating rating = new Rating();
//			rS.create(rating);
//			
//			return "redirect:/ideas";
//		}
//		return "redirect:/users/new";
//	}
//	
//	@RequestMapping("/unlike/{idea_id}")
//	public String unlikeIdea(@PathVariable("idea_id")Long idea_id, HttpSession session) {
//		if(session.getAttribute("id") != null) {
//			Long user_id = (Long) session.getAttribute("id");
//
//			lS.deletefrom(show_id,user_id);
//			
//			return "redirect:/shows";
//		}
//		return "redirect:/users/new";
//	}
//	
//	@RequestMapping("/{id}")
//	public String show(@PathVariable("id") Long id, Model model) {
//		Optional<Show> show = iS.find(id);
//		Show i = show.get();
//		
//		model.addAttribute("show",i);
//		
//		return "show.jsp";
//	}
//	
//	@RequestMapping("/{id}/edit")
//	public String editShow(@PathVariable("id")Long id,@ModelAttribute("show")Show show,Model model, HttpSession session) {
//		if(session.getAttribute("id") == null) {
//			return "redirect:/users/new";
//		}else {
//			
//			Long user_id = (Long) session.getAttribute("id");
//			Optional<Show> myShow = iS.find(id);
//			Show i = myShow.get();
//			
//			Long creator = i.getCreator().getId();
//			if(user_id.equals(creator)) {
//				model.addAttribute("show", i);
//				return "editShow.jsp";
//			}
//
//			return "redirect:/shows";
//		}
//	}
//	
//	@PostMapping("/{id}/edit")
//	public String updateShow(@PathVariable("id")Long id,@ModelAttribute("show")Show show, HttpSession session) {
//		if(session.getAttribute("id") == null) {
//			return "redirect:/users/new";
//		}else {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//
//			show.setCreator(u);
//			iS.update(show);
//			return "redirect:/shows";
//
//		}
//	}
//	
//	@RequestMapping("/delete/{id}")
//	public String deleteShow(@PathVariable("id")Long id) {
//		iS.destroy(id);
//		
//		return "redirect:/shows";
//	}
//	
//	@RequestMapping("/low")
//    public String sortLow(Model model, HttpSession session) {
//		if(session.getAttribute("id") != null) {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//	        model.addAttribute("user",u);
//			
//	        List<Show> shows = iS.all();
//	        show temp;
//	        for(int i=0; i<shows.size(); i++) {
//	            for(int x=1; x<(shows.size()-i); x++) {
//	                if(shows.get(x-1).getLikers().size() >= shows.get(x).getLikers().size() ) {
//	                    temp = shows.get(x-1);
//	                    shows.set(x-1, shows.get(x));
//	                    shows.set(x, temp);
//	                }
//	            }
//	        }
//	        model.addAttribute("shows", shows);
//	        return "shows.jsp";
//		}
//		return "redirect:/users/new";
//    }
//    @RequestMapping("/high")
//    public String sortHigh(Model model, HttpSession session) {
//		if(session.getAttribute("id") != null) {
//			Optional<User> user = uS.find((Long) session.getAttribute("id"));
//			User u = user.get();
//	        model.addAttribute("user",u);
//			
//	        List<Show> shows = iS.all();
//
//	        show temp;
//	        for(int i=0; i<shows.size(); i++) {
//	            for(int x=1; x<(shows.size()-i); x++) {
//	                if(shows.get(x-1).getLikers().size() < shows.get(x).getLikers().size() ) {
//	                    temp = shows.get(x-1);
//	                    shows.set(x-1, shows.get(x));
//	                    shows.set(x, temp);
//	                }
//	            }
//	        }
//	        model.addAttribute("shows", shows);
//	        return "shows.jsp";
//		}
//		return "redirect:/users/new";
//    }
		
}