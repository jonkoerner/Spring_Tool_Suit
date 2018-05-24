package com.jonathan.idea.controllers;

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

import com.jonathan.idea.models.Idea;
import com.jonathan.idea.models.Liker;
import com.jonathan.idea.models.User;
import com.jonathan.idea.services.IdeaService;
import com.jonathan.idea.services.LikerService;
import com.jonathan.idea.services.UserService;

import ch.qos.logback.classic.net.SyslogAppender;


@Controller
@RequestMapping("/ideas")
public class IdeaController {
	private IdeaService iS;
	private UserService uS;
	private LikerService lS;
	
	public IdeaController(IdeaService iS,UserService uS,LikerService lS) {
		this.iS = iS;
		this.uS = uS;
		this.lS = lS;
	}
	
	@RequestMapping("")
	public String allIdeas(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			System.out.println((u.getEmail()+ u.getName()));
			model.addAttribute("user",u);
			System.out.println(iS.all());
			// This passes all of ideas through to my view
			model.addAttribute("ideas",iS.all());
			return "ideas.jsp";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/new")
	public String newIdea(Model model, HttpSession session, @ModelAttribute("idea") Idea idea) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}	
		return "newIdea.jsp";
	}
	
	@PostMapping("/new")
	public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult res,RedirectAttributes flash, HttpSession session) {
		if (res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/courses/new";
		}
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			
			idea.setCreator(u);
			iS.create(idea);
			return "redirect:/ideas";
		}
		
		return "redirect:/new";
	}
	
	@RequestMapping("/like/{id}")
	public String likeIdea(@PathVariable("id")Long id, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
		
			Optional<Idea> idea = iS.find(id);
			Idea i = idea.get();
			
			Liker like = new Liker(u,i);
			lS.create(like);
			
			return "redirect:/ideas";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/unlike/{idea_id}")
	public String unlikeIdea(@PathVariable("idea_id")Long idea_id, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Long user_id = (Long) session.getAttribute("id");

			lS.deletefrom(idea_id,user_id);
			
			return "redirect:/ideas";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/{id}")
	public String idea(@PathVariable("id") Long id, Model model) {
		Optional<Idea> idea = iS.find(id);
		Idea i = idea.get();
		
		model.addAttribute("idea",i);
		
		return "idea.jsp";
	}
	
	@RequestMapping("/{id}/edit")
	public String editIdea(@PathVariable("id")Long id,@ModelAttribute("idea")Idea idea,Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			
			Long user_id = (Long) session.getAttribute("id");
			Optional<Idea> myIdea = iS.find(id);
			Idea i = myIdea.get();
			
			Long creator = i.getCreator().getId();
			if(user_id.equals(creator)) {
				model.addAttribute("idea", i);
				return "editIdea.jsp";
			}

			return "redirect:/ideas";
		}
	}
	
	@PostMapping("/{id}/edit")
	public String updateIdea(@PathVariable("id")Long id,@ModelAttribute("idea")Idea idea, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();

			idea.setCreator(u);
			iS.update(idea);
			return "redirect:/ideas";

		}
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteIdea(@PathVariable("id")Long id) {
		iS.destroy(id);
		
		return "redirect:/ideas";
	}
	
	@RequestMapping("/low")
    public String sortLow(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
	        model.addAttribute("user",u);
			
	        List<Idea> ideas = iS.all();
	        Idea temp;
	        for(int i=0; i<ideas.size(); i++) {
	            for(int x=1; x<(ideas.size()-i); x++) {
	                if(ideas.get(x-1).getLikers().size() >= ideas.get(x).getLikers().size() ) {
	                    temp = ideas.get(x-1);
	                    ideas.set(x-1, ideas.get(x));
	                    ideas.set(x, temp);
	                }
	            }
	        }
	        model.addAttribute("ideas", ideas);
	        return "ideas.jsp";
		}
		return "redirect:/users/new";
    }
    @RequestMapping("/high")
    public String sortHigh(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
	        model.addAttribute("user",u);
			
	        List<Idea> ideas = iS.all();

	        Idea temp;
	        for(int i=0; i<ideas.size(); i++) {
	            for(int x=1; x<(ideas.size()-i); x++) {
	                if(ideas.get(x-1).getLikers().size() < ideas.get(x).getLikers().size() ) {
	                    temp = ideas.get(x-1);
	                    ideas.set(x-1, ideas.get(x));
	                    ideas.set(x, temp);
	                }
	            }
	        }
	        model.addAttribute("ideas", ideas);
	        return "ideas.jsp";
		}
		return "redirect:/users/new";
    }
		
}